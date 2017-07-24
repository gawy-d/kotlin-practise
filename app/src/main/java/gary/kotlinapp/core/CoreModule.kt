package gary.kotlinapp.core

import dagger.Module
import gary.kotlinapp.core.application.ApplicationModule
import gary.kotlinapp.core.network.NetworkModule
import gary.kotlinapp.core.parsing.ParsingModule
import gary.kotlinapp.core.scheduler.SchedulerModule
import gary.kotlinapp.core.view.ViewModule

@Module(includes = arrayOf(
    ApplicationModule::class,
    NetworkModule::class,
    SchedulerModule::class,
    ParsingModule::class,
    ViewModule::class
)) class CoreModule