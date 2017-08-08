package gary.kotlinapp.home.view

import gary.kotlinapp.core.functions.startActivity
import gary.kotlinapp.twitch.view.home.TwitchHomeActivity

class HomeRouter : HomeContracts.Router {

    private var activity: HomeActivity? = null

    override fun create(
        activity: HomeActivity
    ) {
        this.activity = activity
    }

    override fun destroy() {
        this.activity = null
    }

    override fun launchTwitchHome() = activity?.let { startActivity<TwitchHomeActivity>(it) } ?: Unit

}