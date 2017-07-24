package gary.kotlinapp.core.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    @UiScheduler
    fun uiScheduler(): Scheduler

    @NetworkScheduler
    fun networkScheduler(): Scheduler

    @ComputationScheduler
    fun computationScheduler(): Scheduler

}