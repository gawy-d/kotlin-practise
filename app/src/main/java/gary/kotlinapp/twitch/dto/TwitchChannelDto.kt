package gary.kotlinapp.twitch.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class TwitchChannelDto(
    @SerializedName("broadcaster_language") val broadcasterLanguage: String,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("followers") val followers: Int,
    @SerializedName("game") val game: String,
    @SerializedName("language") val language: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("mature") val mature: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("partner") val partner: Boolean,
    @SerializedName("profile_banner") val profileBanner: String,
    @SerializedName("profile_banner_background_color") val profileBannerBackgroundColor: String,
    @SerializedName("status") val status: String,
    @SerializedName("updated_at") val updatedAt: Date,
    @SerializedName("url") val url: String,
    @SerializedName("video_banner") val videoBanner: String,
    @SerializedName("views") val views: Int
)