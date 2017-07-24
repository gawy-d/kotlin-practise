package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.twitch.api.TwitchApiService
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class TwitchHomeInteractor(
    private val twitchApiService: TwitchApiService,
    private val uiScheduler: Scheduler
) : TwitchHomeContracts.Interactor {

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    private var presenter: TwitchHomeContracts.Presenter? = null

    override fun bind(presenter: TwitchHomeContracts.Presenter) {
        this.presenter = presenter
    }

    override fun unbind() {
        subscriptions.clear()
        presenter = null
    }

    override fun searchChannels(
        query: String,
        page: Int
    ) {
        subscriptions.add(
            twitchApiService.searchChannels(query, page).observeOn(uiScheduler).subscribe(this::handleChannels,
                this::handleError))
    }

    private fun handleChannels(channels: TwitchChannels) =
        when (channels.total) {
            0 -> presenter?.onNoChannelsFound()
            else -> presenter?.onChannelsFound(channels)
        } ?: Unit

    private fun handleError(error: Throwable) =
        presenter?.onError(error.message ?: "") ?: Unit
}