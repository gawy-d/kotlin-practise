package gary.kotlinapp.home.view

import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = arrayOf(HomeModule::class))
interface HomeComponent : HomeProvider {

    fun inject(activity: HomeActivity)

}