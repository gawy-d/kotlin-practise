package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class TwitchHomePresenter(
    private val uiScheduler: Scheduler,
    private val subscriptions: CompositeDisposable = CompositeDisposable()
) : TwitchHomeContracts.Presenter {

    private var screen: TwitchHomeContracts.View? = null
    private var interactor: TwitchHomeContracts.Interactor? = null
    private var router: TwitchHomeContracts.Router? = null
    private var listAdapter: TwitchHomeContracts.View.ListAdapter? = null
    private var scrollListener: TwitchHomeContracts.View.OnScrollListener? = null

    private var currentQuery: String = ""
    private var currentPage = 0
    private var total = 0

    override fun onCreate(
        screen: TwitchHomeContracts.View,
        listAdapter: TwitchHomeContracts.View.ListAdapter,
        scrollListener: TwitchHomeContracts.View.OnScrollListener,
        interactor: TwitchHomeContracts.Interactor,
        router: TwitchHomeContracts.Router
    ) {
        this.screen = screen
        this.interactor = interactor
        this.router = router
        this.listAdapter = listAdapter
        this.scrollListener = scrollListener

        interactor.bind(this)
    }

    override fun onDestroy() {
        interactor?.unbind()
        subscriptions.clear()

        this.router = null
        this.interactor = null
        this.screen = null
    }

    override fun init(queryChanges: Observable<CharSequence>) {
        subscriptions.add(queryChanges.debounce(300, TimeUnit.MILLISECONDS)
            .map(CharSequence::toString)
            .observeOn(uiScheduler)
            .subscribe({ query ->
                currentQuery = query
                currentPage = 0
                loadResults()
            }))

        listAdapter?.setOnItemClickListener { onItemClicked(it) }
        scrollListener?.setLoadMoreDataAction { loadResults() }
    }

    override fun onChannelsFound(channels: TwitchChannels) {
        val reset = currentPage == 0

        screen?.hideLoaderWithAnimation()
        listAdapter?.addAll(channels.channels, reset)

        total = channels.total
        ++currentPage

        if (reset) {
            scrollListener?.reset()
        }
    }

    override fun onNoChannelsFound() {
        screen?.hideLoaderWithAnimation()
        clearResults()
    }

    override fun onError(error: String) {
        screen?.hideLoaderWithAnimation()
        screen?.displayError(error)
    }

    private fun onItemClicked(channel: TwitchChannel) {
        // Do something
    }

    private fun clearResults() {
        listAdapter?.clear()
        scrollListener?.reset()
    }

    private fun loadResults() =
        when {
            currentQuery.isEmpty() -> clearResults()
            else -> {
                screen?.displayLoaderWithAnimation()
                interactor?.searchChannels(currentQuery, currentPage)
            }
        } ?: Unit
}