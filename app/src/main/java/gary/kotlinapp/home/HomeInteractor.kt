package gary.kotlinapp.home

import gary.kotlinapp.twitch.api.TwitchApiManager
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class HomeInteractor(
    private val twitchApiManager: TwitchApiManager,
    private val uiScheduler: Scheduler
) : HomeContracts.Interactor {

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    private var presenter: HomeContracts.Presenter? = null

    override fun bind(presenter: HomeContracts.Presenter) {
        this.presenter = presenter
    }

    override fun unbind() {
        subscriptions.clear()
        presenter = null
    }

    override fun searchChannels(query: String) {
        subscriptions.add(twitchApiManager.searchChannels(query).observeOn(uiScheduler).subscribe(this::handleChannels,
            this::handleError))
    }

    private fun handleChannels(channels: TwitchChannels) {
        val results = channels.channels

        when {
            results.isNotEmpty() -> presenter?.onChannelsFetched(channels.channels)
            else -> presenter?.onError("No channel found")
        }
    }

    private fun handleError(error: Throwable) {
        val message = error.message

        if (message != null) {
            presenter?.onError(message)
        }
    }
}