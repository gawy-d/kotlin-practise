package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.core.view.InfiniteScrollListener
import gary.kotlinapp.twitch.model.TwitchChannels
import gary.kotlinapp.twitch.view.home.channel.item.TwitchHomeChannelItemAdapter
import io.reactivex.Observable

class TwitchHomeContracts {

    interface View {

        fun displayError(error: String)

        fun displayLoaderWithAnimation()

        fun hideLoaderWithAnimation()

    }

    interface Interactor {

        fun bind(presenter: Presenter)

        fun unbind()

        fun searchChannels(
            query: String,
            page: Int
        )

    }

    interface Presenter {

        fun onCreate(
            screen: View,
            interactor: Interactor,
            router: Router,
            twitchChannelItemAdapter: TwitchHomeChannelItemAdapter,
            infiniteScrollListener: InfiniteScrollListener
        )

        fun onDestroy()

        fun init(queryChanges: Observable<CharSequence>)

        fun onChannelsFound(channels: TwitchChannels)

        fun onNoChannelsFound()

        fun onError(error: String)

    }

    interface Router {

        fun onCreate(activity: TwitchHomeActivity)

        fun onDestroy()

    }
}