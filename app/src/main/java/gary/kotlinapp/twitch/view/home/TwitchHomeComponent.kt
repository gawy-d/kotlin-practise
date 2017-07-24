package gary.kotlinapp.twitch.view.home

import dagger.Subcomponent

@TwitchHomeScope
@Subcomponent(modules = arrayOf(TwitchHomeModule::class))
interface TwitchHomeComponent : TwitchHomeProvider {

    fun inject(activity: TwitchHomeActivity)

}