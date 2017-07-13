package gary.kotlinapp.home

import gary.kotlinapp.twitch.model.TwitchChannel
import io.reactivex.Observable

class HomeContracts {

    interface View {

        fun displayChannels(channels: String)

        fun hideChannels()

        fun displayError(error: String)

        fun displayLoader()

        fun hideLoader()

        fun displayLoaderWithAnimation()

        fun hideLoaderWithAnimation()

    }

    interface Presenter {

        fun onCreate(screen: View, interactor: Interactor, router: Router)

        fun onDestroy()

        fun init(queryChanges: Observable<CharSequence>)

        fun onChannelsFetched(channels: List<TwitchChannel>)

        fun onError(error: String)

    }

    interface Interactor {

        fun bind(presenter: Presenter)

        fun unbind()

        fun searchChannels(query: String)

    }

    interface Router
}