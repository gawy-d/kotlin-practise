package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.R
import gary.kotlinapp.core.application.resource.ResourceProvider
import gary.kotlinapp.core.view.toolbar.ToolbarBuilder
import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class TwitchHomePresenter(
    private val uiScheduler: Scheduler,
    private val resourceProvider: ResourceProvider,
    private val subscriptions: CompositeDisposable = CompositeDisposable()
) : TwitchHomeContracts.Presenter, TwitchHomeContracts.Interactor.Callbacks {

    private var screen: TwitchHomeContracts.View? = null
    private var interactor: TwitchHomeContracts.Interactor? = null
    private var router: TwitchHomeContracts.Router? = null
    private var listAdapter: TwitchHomeContracts.View.ListAdapter? = null
    private var scrollListener: TwitchHomeContracts.View.OnScrollListener? = null
    private var queryChanges: Observable<CharSequence>? = null
    private var toolbarBuilder: ToolbarBuilder? = null

    private var currentQuery: String = ""
    private var currentPage = 0
    private var total = 0

    override fun onCreate(
        screen: TwitchHomeContracts.View,
        listAdapter: TwitchHomeContracts.View.ListAdapter,
        scrollListener: TwitchHomeContracts.View.OnScrollListener,
        queryChanges: Observable<CharSequence>,
        toolbarBuilder: ToolbarBuilder,
        interactor: TwitchHomeContracts.Interactor,
        router: TwitchHomeContracts.Router
    ) {
        this.screen = screen
        this.listAdapter = listAdapter
        this.scrollListener = scrollListener
        this.queryChanges = queryChanges
        this.toolbarBuilder = toolbarBuilder
        this.interactor = interactor
        this.router = router

        start()
    }

    override fun onDestroy() {
        interactor?.unbind()

        subscriptions.clear()

        router = null
        interactor = null
        toolbarBuilder = null
        queryChanges = null
        scrollListener = null
        listAdapter = null
        screen = null
    }

    override fun onChannelsFound(channels: TwitchChannels) {
        val reset = currentPage == 0

        screen?.hideLoader()
        listAdapter?.addAll(channels.channels, reset)

        total = channels.total
        ++currentPage

        if (reset) {
            scrollListener?.reset()
        }
    }

    override fun onNoChannelsFound() {
        screen?.hideLoader()
        clearResults()
    }

    override fun onError(error: String) {
        screen?.apply {
            hideLoader()
            displayError(error)
        }
    }

    private fun start() {
        interactor?.bind(this)

        listAdapter?.setOnItemClickListener { onItemClicked(it) }
        scrollListener?.setLoadMoreDataAction { loadResults() }

        toolbarBuilder?.build(resourceProvider.getString(R.string.title_twitch_home), displayHomeAsUp = true)

        queryChanges?.let {
            subscriptions.add(it.debounce(300, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .observeOn(uiScheduler)
                .subscribe {
                    currentQuery = it
                    currentPage = 0

                    loadResults()
                }
            )
        }
    }

    private fun onItemClicked(channel: TwitchChannel) {
        // Do something
    }

    private fun clearResults() {
        listAdapter?.clear()
        scrollListener?.reset()
    }

    private fun loadResults() =
        if (currentQuery.isEmpty()) {
            clearResults()
        } else {
            screen?.displayLoader()
            interactor?.searchChannels(currentQuery, currentPage)
        }
}