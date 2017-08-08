package gary.kotlinapp.twitch.api

import gary.kotlinapp.twitch.mapper.TwitchChannelsDtoTwitchChannelsMapper
import gary.kotlinapp.twitch.model.TwitchChannels
import gary.kotlinapp.twitch.repository.TwitchChannelsRepository
import io.reactivex.Single

class TwitchApiServiceImpl(
    private val channelsRepository: TwitchChannelsRepository,
    private val channelsMapper: TwitchChannelsDtoTwitchChannelsMapper
) : TwitchApiService {

    override fun searchChannels(
        query: String,
        page: Int
    ): Single<TwitchChannels> = channelsRepository.searchChannels(query, page).map(channelsMapper)

}