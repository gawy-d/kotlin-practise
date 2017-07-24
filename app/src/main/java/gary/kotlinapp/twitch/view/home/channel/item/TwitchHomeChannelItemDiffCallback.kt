package gary.kotlinapp.twitch.view.home.channel.item

import android.support.v7.util.DiffUtil
import gary.kotlinapp.twitch.model.TwitchChannel

class TwitchHomeChannelItemDiffCallback(
    val oldList: List<TwitchChannel>,
    val newList: List<TwitchChannel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() =
        oldList.size

    override fun getNewListSize() =
        newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}