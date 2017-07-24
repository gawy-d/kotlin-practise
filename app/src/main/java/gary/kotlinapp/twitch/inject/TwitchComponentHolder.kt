package gary.kotlinapp.twitch.inject

import gary.kotlinapp.application.KotlinApplicationComponent
import gary.kotlinapp.core.inject.ComponentHolder
import gary.kotlinapp.twitch.view.home.TwitchHomeComponentHolder

class TwitchComponentHolder(
    private val applicationComponent: KotlinApplicationComponent
) : ComponentHolder<TwitchComponent> {

    private var component: TwitchComponent? = null
    private var homeComponentHolder: TwitchHomeComponentHolder? = null

    fun getHomeComponentHolder(): TwitchHomeComponentHolder {
        if (homeComponentHolder == null) {
            homeComponentHolder = TwitchHomeComponentHolder(get())
        }

        return homeComponentHolder!!
    }

    override fun get(): TwitchComponent {
        if (component == null) {
            component = applicationComponent.plus(TwitchModule())
        }

        return component!!
    }

    override fun release() {
        component = null
        releaseHomeComponentHolder()
    }

    private fun releaseHomeComponentHolder() {
        homeComponentHolder?.release()
        homeComponentHolder = null
    }
}