package gary.kotlinapp.home.view

import android.content.Intent
import gary.kotlinapp.twitch.view.home.TwitchHomeActivity

class HomeRouter : HomeContracts.Router {

    private var activity: HomeActivity? = null

    override fun create(activity: HomeActivity) {
        this.activity = activity
    }

    override fun destroy() {
        this.activity = null
    }

    override fun launchTwitchHome() {
        val intent = Intent(activity, TwitchHomeActivity::class.java)

        activity?.startActivity(intent)
    }

}