package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.core.inject.ComponentHolder
import gary.kotlinapp.twitch.inject.TwitchComponent

class TwitchHomeComponentHolder(
    private val twitchComponent: TwitchComponent
) : ComponentHolder<TwitchHomeComponent> {

    private var component: TwitchHomeComponent? = null

    override fun get(): TwitchHomeComponent {
        if (component == null) {
            component = twitchComponent.plus(TwitchHomeModule())
        }

        return component!!
    }

    override fun release() {
        component = null
    }

}