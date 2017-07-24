package gary.kotlinapp.twitch.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class TwitchChannel(
    val id: Long,
    val displayName: String,
    val followers: Int,
    val game: String,
    val logo: String?
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelTwitchChannel.CREATOR
    }
}