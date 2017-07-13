package gary.kotlinapp.home

import gary.kotlinapp.twitch.model.TwitchChannel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class HomePresenter(
    private val uiScheduler: Scheduler
) : HomeContracts.Presenter {

    private val subscriptions = CompositeDisposable()

    private var screen: HomeContracts.View? = null
    private var interactor: HomeContracts.Interactor? = null
    private var router: HomeContracts.Router? = null

    override fun onCreate(screen: HomeContracts.View, interactor: HomeContracts.Interactor,
        router: HomeContracts.Router) {
        this.screen = screen
        this.interactor = interactor
        this.router = router

        interactor.bind(this)
    }

    override fun onDestroy() {
        subscriptions.clear()

        interactor?.unbind()

        this.router = null
        this.interactor = null
        this.screen = null
    }

    override fun init(queryChanges: Observable<CharSequence>) {
        screen?.hideLoader()

        subscriptions.add(queryChanges.debounce(300, TimeUnit.MILLISECONDS)
            .map(CharSequence::toString)
            .observeOn(uiScheduler)
            .subscribe({ query ->
                handleQuery(query)
            }))
    }

    override fun onChannelsFetched(channels: List<TwitchChannel>) {
        screen?.hideLoaderWithAnimation()
        screen?.displayChannels(channels.map {
            "- "
                .plus(it.displayName)
                .plus(" (")
                .plus(it.followers)
                .plus(" followers) - ")
                .plus(it.game)
        }.joinToString(separator = "\n\n"))
    }

    override fun onError(error: String) {
        screen?.hideLoader()
        screen?.displayError(error)
    }

    private fun handleQuery(query: String) {
        when {
            query.isEmpty() -> screen?.hideChannels()
            else -> {
                screen?.displayLoaderWithAnimation()
                interactor?.searchChannels(query)
            }
        }
    }
}