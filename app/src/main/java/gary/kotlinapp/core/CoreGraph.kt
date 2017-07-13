package gary.kotlinapp.core

import gary.kotlinapp.core.application.ApplicationGraph
import gary.kotlinapp.core.network.NetworkGraph
import gary.kotlinapp.core.parsing.ParsingGraph
import gary.kotlinapp.core.scheduler.SchedulerGraph

interface CoreGraph : ApplicationGraph, NetworkGraph, SchedulerGraph, ParsingGraph