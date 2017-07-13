package gary.kotlinapp.twitch.api

import gary.kotlinapp.twitch.mapper.TwitchChannelsDtoTwitchChannelsMapper
import gary.kotlinapp.twitch.model.TwitchChannels
import gary.kotlinapp.twitch.repository.TwitchChannelsRepository
import io.reactivex.Single

class TwitchApiManager(
    private val channelsRepository: TwitchChannelsRepository,
    private val channelsMapper: TwitchChannelsDtoTwitchChannelsMapper
) {

    fun searchChannels(query: String): Single<TwitchChannels> {
        return channelsRepository.searchChannels(query).map(channelsMapper)
    }

}