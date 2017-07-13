package gary.kotlinapp.twitch.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class TwitchChannel(
    val displayName: String,
    val followers: Int,
    val game: String
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelTwitchChannel.CREATOR
    }
}