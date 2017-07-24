package gary.kotlinapp.twitch.inject

import gary.kotlinapp.twitch.api.TwitchApiService
import gary.kotlinapp.twitch.view.home.TwitchHomeProvider

interface TwitchProvider : TwitchHomeProvider {

    fun twitchApiService(): TwitchApiService

}