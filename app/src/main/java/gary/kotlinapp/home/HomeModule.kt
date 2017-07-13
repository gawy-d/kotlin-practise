package gary.kotlinapp.home

import dagger.Module
import dagger.Provides
import gary.kotlinapp.core.scheduler.UiScheduler
import gary.kotlinapp.twitch.api.TwitchApiManager
import io.reactivex.Scheduler

@Module
class HomeModule {

    @Provides
    @HomeScope
    fun presenter(@UiScheduler uiScheduler: Scheduler): HomeContracts.Presenter {
        return HomePresenter(uiScheduler)
    }

    @Provides
    @HomeScope
    fun interactor(twitchApiManager: TwitchApiManager, @UiScheduler uiScheduler: Scheduler): HomeContracts.Interactor {
        return HomeInteractor(twitchApiManager, uiScheduler)
    }

}