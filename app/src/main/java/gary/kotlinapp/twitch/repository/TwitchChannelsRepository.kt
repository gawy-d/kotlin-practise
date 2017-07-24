package gary.kotlinapp.twitch.repository

import gary.kotlinapp.twitch.api.TwitchApi

class TwitchChannelsRepository(
    private val api: TwitchApi
) {

    fun searchChannels(
        query: String,
        page: Int
    ) =
        api.searchChannels(query, page)

}