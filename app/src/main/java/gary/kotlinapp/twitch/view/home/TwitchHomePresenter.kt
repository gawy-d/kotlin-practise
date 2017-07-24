package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.core.view.InfiniteScrollListener
import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import gary.kotlinapp.twitch.view.home.channel.item.TwitchHomeChannelItemAdapter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class TwitchHomePresenter(
    private val uiScheduler: Scheduler
) : TwitchHomeContracts.Presenter {

    private val subscriptions = CompositeDisposable()

    private var screen: TwitchHomeContracts.View? = null
    private var interactor: TwitchHomeContracts.Interactor? = null
    private var router: TwitchHomeContracts.Router? = null
    private var twitchChannelItemAdapter: TwitchHomeChannelItemAdapter? = null
    private var infiniteScrollListener: InfiniteScrollListener? = null

    private var currentQuery: String = ""
    private var currentPage = 0
    private var total = 0

    override fun onCreate(
        screen: TwitchHomeContracts.View,
        interactor: TwitchHomeContracts.Interactor,
        router: TwitchHomeContracts.Router,
        twitchChannelItemAdapter: TwitchHomeChannelItemAdapter,
        infiniteScrollListener: InfiniteScrollListener
    ) {
        this.screen = screen
        this.interactor = interactor
        this.router = router
        this.twitchChannelItemAdapter = twitchChannelItemAdapter
        this.infiniteScrollListener = infiniteScrollListener

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

        infiniteScrollListener?.setOnLoadMoreDataListener { loadResults() }
        twitchChannelItemAdapter?.setOnItemClickListener { onItemClicked(it) }
    }

    override fun onChannelsFound(channels: TwitchChannels) {
        val reset = currentPage == 0

        screen?.hideLoaderWithAnimation()
        twitchChannelItemAdapter?.addAll(channels.channels, reset)

        total = channels.total
        ++currentPage

        if (reset) {
            infiniteScrollListener?.reset()
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
        twitchChannelItemAdapter?.clear()
        infiniteScrollListener?.reset()
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