package gary.kotlinapp.twitch.inject

import gary.kotlinapp.twitch.api.TwitchApiManager

interface TwitchGraph {

    fun twitchApiManager(): TwitchApiManager

}