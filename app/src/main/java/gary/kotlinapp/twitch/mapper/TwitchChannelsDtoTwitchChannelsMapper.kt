package gary.kotlinapp.twitch.mapper

import gary.kotlinapp.twitch.dto.TwitchChannelsDto
import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.functions.Function

class TwitchChannelsDtoTwitchChannelsMapper : Function<TwitchChannelsDto, TwitchChannels> {

    override fun apply(channelsDto: TwitchChannelsDto): TwitchChannels {
        val channels = channelsDto.channels.map {
            TwitchChannel(it.displayName, it.followers, it.game)
        }

        return TwitchChannels(channels.size, channels)
    }
}