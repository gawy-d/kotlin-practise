package gary.kotlinapp.twitch.view.home

class TwitchHomeRouter : TwitchHomeContracts.Router {

    private var activity: TwitchHomeActivity? = null

    override fun onCreate(activity: TwitchHomeActivity) {
        this.activity = activity
    }

    override fun onDestroy() {
        this.activity = null
    }

}