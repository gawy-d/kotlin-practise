package gary.kotlinapp.application

import gary.kotlinapp.home.HomeComponentHolder

class KotlinApplicationComponentHolder(
    applicationComponent: KotlinApplicationComponent
) {

    val home = HomeComponentHolder(applicationComponent)

}