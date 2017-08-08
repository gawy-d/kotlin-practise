package gary.kotlinapp.twitch.mapper

import gary.kotlinapp.twitch.api.dto.TwitchChannelsDto
import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.functions.Function

class TwitchChannelsDtoTwitchChannelsMapper : Function<TwitchChannelsDto, TwitchChannels> {

    override fun apply(
        channelsDto: TwitchChannelsDto
    ): TwitchChannels = channelsDto.channels.map {
        TwitchChannel(it.id, it.displayName, it.followers, it.game, it.logo)
    }.let {
        TwitchChannels(it.size, it)
    }
}