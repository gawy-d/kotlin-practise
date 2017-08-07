package gary.kotlinapp.twitch.view.home

class TwitchHomeRouter : TwitchHomeContracts.Router {

    private var activity: TwitchHomeActivity? = null

    override fun create(activity: TwitchHomeActivity) {
        this.activity = activity
    }

    override fun destroy() {
        this.activity = null
    }

}