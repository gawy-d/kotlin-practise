package gary.kotlinapp.core.scheduler

import io.reactivex.Scheduler

interface SchedulerGraph {

    @UiScheduler
    fun uiScheduler(): Scheduler

    @NetworkScheduler
    fun networkScheduler(): Scheduler

    @ComputationScheduler
    fun computationScheduler(): Scheduler

}