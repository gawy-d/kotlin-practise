package gary.kotlinapp.twitch.view.home.channel.item

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import gary.kotlinapp.R
import gary.kotlinapp.application.GlideApp
import gary.kotlinapp.twitch.model.TwitchChannel
import kotlinx.android.synthetic.main.view_twitch_home_channel.view.*

class TwitchHomeChannelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        init()
    }

    private fun init() =
        inflate(context, R.layout.view_twitch_home_channel, this)

    fun bind(channel: TwitchChannel) {
        channelTitle.text = channel.displayName
        channelFollowers.text = "%d followers".format(channel.followers)

        GlideApp.with(this).load(channel.logo).circleCrop().into(channelLogo)
    }

    override fun setOnClickListener(l: OnClickListener?) =
        channelLayout.setOnClickListener(l)
}