package gary.kotlinapp.twitch.repository

import gary.kotlinapp.twitch.api.TwitchApi
import gary.kotlinapp.twitch.api.dto.TwitchChannelsDto
import io.reactivex.Single

class TwitchChannelsRepository(
    private val api: TwitchApi
) {

    fun searchChannels(
        query: String,
        page: Int
    ): Single<TwitchChannelsDto> = api.searchChannels(query, page)

}