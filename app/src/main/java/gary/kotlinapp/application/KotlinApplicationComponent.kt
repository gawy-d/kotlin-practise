package gary.kotlinapp.application

import dagger.Component
import gary.kotlinapp.core.CoreGraph
import gary.kotlinapp.core.CoreModule
import gary.kotlinapp.home.HomeGraph
import gary.kotlinapp.twitch.inject.TwitchGraph
import gary.kotlinapp.twitch.inject.TwitchModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(KotlinApplicationModule::class, CoreModule::class, TwitchModule::class))
interface KotlinApplicationComponent : CoreGraph, TwitchGraph, HomeGraph