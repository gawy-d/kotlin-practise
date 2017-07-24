package gary.kotlinapp.home.view

class HomePresenter : HomeContracts.Presenter {

    private var screen: HomeContracts.View? = null
    private var router: HomeContracts.Router? = null

    override fun onCreate(screen: HomeContracts.View, router: HomeContracts.Router) {
        this.screen = screen
        this.router = router
    }

    override fun onDestroy() {
        this.router = null
        this.screen = null
    }

    override fun onTwitchCTAClicked() =
        router?.launchTwitchHome() ?: Unit
}