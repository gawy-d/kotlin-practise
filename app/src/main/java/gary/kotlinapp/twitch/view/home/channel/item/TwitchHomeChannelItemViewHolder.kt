package gary.kotlinapp.twitch.view.home.channel.item

import android.support.v7.widget.RecyclerView
import android.view.View
import gary.kotlinapp.R
import gary.kotlinapp.twitch.model.TwitchChannel

class TwitchHomeChannelItemViewHolder(
    itemView: View,
    private val onItemClickAction: ((TwitchChannel) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {

    val twitchChannelView: TwitchHomeChannelView? by lazy {
        itemView.findViewById<TwitchHomeChannelView>(R.id.itemTwitchChannel)
    }

    fun bind(
        channel: TwitchChannel
    ) {
        twitchChannelView?.bind(channel)
        twitchChannelView?.setOnClickListener { onItemClickAction?.invoke(channel) }
    }
}