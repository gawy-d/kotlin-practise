package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.twitch.api.TwitchApiService
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class TwitchHomeInteractor(
    private val twitchApiService: TwitchApiService,
    private val uiScheduler: Scheduler,
    private val subscriptions: CompositeDisposable = CompositeDisposable()
) : TwitchHomeContracts.Interactor {

    private var callbacks: TwitchHomeContracts.Interactor.Callbacks? = null

    override fun bind(callbacks: TwitchHomeContracts.Interactor.Callbacks) {
        this.callbacks = callbacks
    }

    override fun unbind() {
        subscriptions.clear()
        callbacks = null
    }

    override fun searchChannels(
        query: String,
        page: Int
    ) {
        subscriptions.add(twitchApiService.searchChannels(query, page)
            .observeOn(uiScheduler)
            .subscribe(this::handleChannels, this::handleError)
        )
    }

    private fun handleChannels(channels: TwitchChannels) =
        when (channels.total) {
            0 -> callbacks?.onNoChannelsFound()
            else -> callbacks?.onChannelsFound(channels)
        } ?: Unit

    private fun handleError(error: Throwable) =
        callbacks?.onError(error.message ?: "") ?: Unit
}