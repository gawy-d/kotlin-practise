package gary.kotlinapp.twitch.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class TwitchChannels(
    val total: Int,
    val channels: List<TwitchChannel>
): PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelTwitchChannels.CREATOR
    }
}