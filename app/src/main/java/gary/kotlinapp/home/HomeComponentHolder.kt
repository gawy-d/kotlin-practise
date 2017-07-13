package gary.kotlinapp.home

import gary.kotlinapp.application.KotlinApplicationComponent

class HomeComponentHolder(
    private val applicationComponent: KotlinApplicationComponent
) {

    private var homeComponent: HomeComponent? = null

    fun get(): HomeComponent {
        if (homeComponent == null) {
            homeComponent = applicationComponent.plus(HomeModule())
        }

        return homeComponent!!
    }

    fun release() {
        homeComponent = null
    }

}