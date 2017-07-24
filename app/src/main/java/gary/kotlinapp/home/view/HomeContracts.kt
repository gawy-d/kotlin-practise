package gary.kotlinapp.home.view

class HomeContracts {

    interface View

    interface Presenter {

        fun onCreate(
            screen: View,
            router: Router
        )

        fun onDestroy()

        fun onTwitchCTAClicked()

    }

    interface Router {

        fun onCreate(activity: HomeActivity)

        fun onDestroy()

        fun launchTwitchHome()

    }
}