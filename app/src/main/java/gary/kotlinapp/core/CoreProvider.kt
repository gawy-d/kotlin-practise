package gary.kotlinapp.core

import gary.kotlinapp.core.application.ApplicationProvider
import gary.kotlinapp.core.network.NetworkProvider
import gary.kotlinapp.core.parsing.ParsingProvider
import gary.kotlinapp.core.scheduler.SchedulerProvider
import gary.kotlinapp.core.view.ViewProvider

interface CoreProvider : ApplicationProvider, NetworkProvider, SchedulerProvider, ParsingProvider, ViewProvider