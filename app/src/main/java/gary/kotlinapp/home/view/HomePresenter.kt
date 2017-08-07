package gary.kotlinapp.home.view

import gary.kotlinapp.R
import gary.kotlinapp.core.application.resource.ResourceProvider
import gary.kotlinapp.core.view.toolbar.ToolbarBuilder
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val resourceProvider: ResourceProvider
) : HomeContracts.Presenter {

    private var screen: HomeContracts.View? = null
    private var toolbarBuilder: ToolbarBuilder? = null
    private var router: HomeContracts.Router? = null

    override fun onCreate(
        screen: HomeContracts.View,
        toolbarBuilder: ToolbarBuilder,
        router: HomeContracts.Router
    ) {
        this.screen = screen
        this.toolbarBuilder = toolbarBuilder
        this.router = router

        start()
    }

    override fun onDestroy() {
        router = null
        toolbarBuilder = null
        screen = null
    }

    override fun onTwitchCTAClicked() =
        router?.launchTwitchHome() ?: Unit

    private fun start() =
        toolbarBuilder?.build(resourceProvider.getString(R.string.title_home)) ?: Unit

}