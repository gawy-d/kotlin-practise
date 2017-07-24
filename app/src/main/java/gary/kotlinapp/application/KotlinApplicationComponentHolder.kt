package gary.kotlinapp.application

import gary.kotlinapp.home.view.HomeComponentHolder
import gary.kotlinapp.twitch.inject.TwitchComponentHolder

class KotlinApplicationComponentHolder(
    applicationComponent: KotlinApplicationComponent
) {

    val homeComponentHolder = HomeComponentHolder(applicationComponent)
    val twitchComponentHolder = TwitchComponentHolder(applicationComponent)

}