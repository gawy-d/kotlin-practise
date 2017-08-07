package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.core.view.toolbar.ToolbarBuilder
import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Observable

class TwitchHomeContracts {

    interface View {

        fun displayError(error: String)

        fun displayLoader()

        fun hideLoader()

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

        fun bind(callbacks: Callbacks)

        fun unbind()

        fun searchChannels(
            query: String,
            page: Int
        )

        interface Callbacks {

            fun onChannelsFound(channels: TwitchChannels)

            fun onNoChannelsFound()

            fun onError(error: String)
        }
    }

    interface Presenter {

        fun onCreate(
            screen: View,
            listAdapter: View.ListAdapter,
            scrollListener: View.OnScrollListener,
            queryChanges: Observable<CharSequence>,
            toolbarBuilder: ToolbarBuilder,
            interactor: Interactor,
            router: Router
        )

        fun onDestroy()
    }

    interface Router {

        fun create(activity: TwitchHomeActivity)

        fun destroy()
    }
}