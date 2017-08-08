package gary.kotlinapp.twitch.view.home.channel.item

import android.support.v7.util.DiffUtil
import gary.kotlinapp.twitch.model.TwitchChannel

class TwitchHomeChannelItemDiffCallback(
    val oldList: List<TwitchChannel>,
    val newList: List<TwitchChannel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

}