package gary.kotlinapp.application

import dagger.Component
import gary.kotlinapp.core.CoreModule
import gary.kotlinapp.core.CoreProvider
import gary.kotlinapp.home.view.HomeComponent
import gary.kotlinapp.home.view.HomeModule
import gary.kotlinapp.twitch.inject.TwitchComponent
import gary.kotlinapp.twitch.inject.TwitchModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(KotlinApplicationModule::class, CoreModule::class))
interface KotlinApplicationComponent : CoreProvider {

    fun plus(module: HomeModule): HomeComponent

    fun plus(module: TwitchModule): TwitchComponent

}