package gary.kotlinapp.twitch.inject

import dagger.Subcomponent
import gary.kotlinapp.twitch.view.home.TwitchHomeComponent
import gary.kotlinapp.twitch.view.home.TwitchHomeModule

@TwitchScope
@Subcomponent(modules = arrayOf(TwitchModule::class))
interface TwitchComponent : TwitchProvider {

    fun plus(module: TwitchHomeModule): TwitchHomeComponent

}