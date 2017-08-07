package gary.kotlinapp.home.view

import gary.kotlinapp.core.view.toolbar.ToolbarBuilder

class HomeContracts {

    interface View

    interface Presenter {

        fun onCreate(
            screen: View,
            toolbarBuilder: ToolbarBuilder,
            router: Router
        )

        fun onDestroy()

        fun onTwitchCTAClicked()

    }

    interface Router {

        fun create(activity: HomeActivity)

        fun destroy()

        fun launchTwitchHome()

    }
}