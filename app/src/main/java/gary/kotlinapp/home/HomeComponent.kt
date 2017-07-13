package gary.kotlinapp.home

import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = arrayOf(HomeModule::class))
interface HomeComponent {

    fun inject(activity: HomeActivity)

}