package gary.kotlinapp.twitch.api.dto

import com.google.gson.annotations.SerializedName

data class TwitchChannelsDto(
    @SerializedName("_total") val total: Int,
    @SerializedName("channels") val channels: List<TwitchChannelDto>
)