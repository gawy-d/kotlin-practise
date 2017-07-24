package gary.kotlinapp.twitch.view.home

import dagger.Module
import dagger.Provides
import gary.kotlinapp.core.scheduler.UiScheduler
import gary.kotlinapp.twitch.api.TwitchApiService
import io.reactivex.Scheduler

@Module
class TwitchHomeModule {

    @Provides
    @TwitchHomeScope
    fun presenter(@UiScheduler uiScheduler: Scheduler): TwitchHomeContracts.Presenter =
        TwitchHomePresenter(uiScheduler)

    @Provides
    @TwitchHomeScope
    fun interactor(
        twitchApiService: TwitchApiService,
        @UiScheduler uiScheduler: Scheduler
    ): TwitchHomeContracts.Interactor =
        TwitchHomeInteractor(twitchApiService, uiScheduler)

    @Provides
    @TwitchHomeScope
    fun router(): TwitchHomeContracts.Router =
        TwitchHomeRouter()

}