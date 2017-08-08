package gary.kotlinapp.home.view

import gary.kotlinapp.core.view.toolbar.ToolbarBuilder

class HomeContracts {

    interface View

    interface Presenter {

        /**
         * Called when the screen is created
         *
         * @param screen The screen
         * @param toolbarBuilder The toolbar builder
         * @param router The router
         */
        fun onCreate(
            screen: View,
            toolbarBuilder: ToolbarBuilder,
            router: Router
        )

        /**
         * Called when the screen is destroyed
         */
        fun onDestroy()

        /**
         * Called when the user clicks on twitch CTA
         */
        fun onTwitchCTAClicked()

    }

    interface Router {

        /**
         * Create the router with host activity
         */
        fun create(
            activity: HomeActivity
        )

        /**
         * Destroy the router
         */
        fun destroy()

        /**
         * Launch Twitch Home screen
         */
        fun launchTwitchHome()

    }
}