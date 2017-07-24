package gary.kotlinapp.home.view

import gary.kotlinapp.application.KotlinApplicationComponent
import gary.kotlinapp.core.inject.ComponentHolder

class HomeComponentHolder(
    private val applicationComponent: KotlinApplicationComponent
) : ComponentHolder<HomeComponent> {

    private var component: HomeComponent? = null

    override fun get(): HomeComponent {
        if (component == null) {
            component = applicationComponent.plus(HomeModule())
        }

        return component!!
    }

    override fun release() {
        component = null
    }

}