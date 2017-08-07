package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Observable

class TwitchHomeContracts {

    interface View {

        fun displayError(error: String)

        fun displayLoaderWithAnimation()

        fun hideLoaderWithAnimation()

        interface ListAdapter {
            fun setOnItemClickListener(onItemClickListener: (TwitchChannel) -> Unit)

            fun addAll(
                channels: List<TwitchChannel>,
                clearList: Boolean
            )

            fun clear()
        }

        interface OnScrollListener {
            fun setLoadMoreDataAction(loadMoreData: () -> Unit)

            fun reset()
        }
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
            listAdapter: View.ListAdapter,
            scrollListener: View.OnScrollListener,
            interactor: Interactor,
            router: Router
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