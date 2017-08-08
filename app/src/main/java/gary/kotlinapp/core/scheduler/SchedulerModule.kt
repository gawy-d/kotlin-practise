package gary.kotlinapp.core.scheduler

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Provides
    @Singleton
    @UiScheduler
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @NetworkScheduler
    fun networkScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @ComputationScheduler
    fun computationScheduler(): Scheduler = Schedulers.computation()

}