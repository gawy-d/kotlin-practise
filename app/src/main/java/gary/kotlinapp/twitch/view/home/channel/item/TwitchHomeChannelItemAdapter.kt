package gary.kotlinapp.twitch.view.home.channel.item

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import gary.kotlinapp.R
import gary.kotlinapp.twitch.model.TwitchChannel
import javax.inject.Inject

class TwitchHomeChannelItemAdapter @Inject constructor() : RecyclerView.Adapter<TwitchHomeChannelItemViewHolder>() {

    private val channelList = arrayListOf<TwitchChannel>()

    private var onItemClickListener: ((TwitchChannel) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (TwitchChannel) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    fun addAll(
        channels: List<TwitchChannel>,
        clearList: Boolean
    ) {
        val oldList = ArrayList(channelList)

        if (clearList) {
            channelList.clear()
        }

        channelList += channels
        dispatchUpdates(oldList, channelList)
    }

    fun clear() {
        val oldList = ArrayList(channelList)

        channelList.clear()
        dispatchUpdates(oldList, channelList)
    }

    fun dispatchUpdates(
        oldList: List<TwitchChannel>,
        newList: List<TwitchChannel>
    ) =
        DiffUtil.calculateDiff(TwitchHomeChannelItemDiffCallback(oldList, newList)).dispatchUpdatesTo(this)

    override fun onBindViewHolder(
        holder: TwitchHomeChannelItemViewHolder,
        position: Int
    ) =
        holder.bind(channelList[position])

    override fun getItemId(position: Int) =
        channelList[position].id

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TwitchHomeChannelItemViewHolder =
        TwitchHomeChannelItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_twitch_home_channel, parent, false),
            onItemClickListener)

    override fun getItemCount(): Int =
        channelList.size

}