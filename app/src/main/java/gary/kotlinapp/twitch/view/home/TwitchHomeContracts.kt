package gary.kotlinapp.twitch.view.home

import gary.kotlinapp.core.view.toolbar.ToolbarBuilder
import gary.kotlinapp.twitch.model.TwitchChannel
import gary.kotlinapp.twitch.model.TwitchChannels
import io.reactivex.Observable

class TwitchHomeContracts {

    interface View {

        /**
         * Display given error
         *
         * @param error The error to display
         */
        fun displayError(
            error: String
        )

        /**
         * Display the loader
         */
        fun displayLoader()

        /**
         * Hide the loader
         */
        fun hideLoader()

        interface ListAdapter {
            /**
             * Set the given action when an item is clicked
             *
             * @param onItemClickAction The action
             */
            fun setOnItemClickAction(
                onItemClickAction: (TwitchChannel) -> Unit
            )

            /**
             * Add channels to the list
             *
             * @param channels The list of channels to add
             * @param clearList True if the list should be cleared before adding items, False otherwise
             */
            fun addAll(
                channels: List<TwitchChannel>,
                clearList: Boolean
            )

            /**
             * Remove all channels from the list
             */
            fun clear()
        }

        interface OnScrollListener {
            /**
             * Set the given action when more data can be loaded
             *
             * @param loadMoreData The action
             */
            fun setLoadMoreDataAction(
                loadMoreData: () -> Unit
            )

            /**
             * Reset the state of the infinite scroll
             */
            fun reset()
        }
    }

    interface Interactor {

        /**
         * Bind the interactor
         *
         * @param callbacks The interactor callbacks
         */
        fun bind(
            callbacks: Callbacks
        )

        /**
         * Unbind the interactor
         */
        fun unbind()

        /**
         * Search for channels
         *
         * @param query The query
         * @param page The page number (for infinite scrolling)
         */
        fun searchChannels(
            query: String,
            page: Int
        )

        interface Callbacks {

            /**
             * Called when channels are found
             *
             * @param channels The channels
             */
            fun onChannelsFound(
                channels: TwitchChannels
            )

            /**
             * Called when no channel are found
             */
            fun onNoChannelsFound()

            /**
             * Called when an error occurred
             *
             * @param error The error
             */
            fun onError(
                error: String
            )
        }
    }

    interface Presenter {

        /**
         * Called when the screen is created
         *
         * @param screen The screen
         * @param listAdapter The channels list adapter
         * @param scrollListener The list scroll listener
         * @param queryChanges The Observable that emits query input changes
         * @param interactor The interactor
         * @param router The router
         */
        fun onCreate(
            screen: View,
            listAdapter: View.ListAdapter,
            scrollListener: View.OnScrollListener,
            queryChanges: Observable<CharSequence>,
            toolbarBuilder: ToolbarBuilder,
            interactor: Interactor,
            router: Router
        )

        /**
         * Called when the screen is destroyed
         */
        fun onDestroy()
    }

    interface Router {

        /**
         * Create the router with host activity
         *
         * @param activity The activity
         */
        fun create(
            activity: TwitchHomeActivity
        )

        /**
         * Destroy the router
         */
        fun destroy()
    }
}