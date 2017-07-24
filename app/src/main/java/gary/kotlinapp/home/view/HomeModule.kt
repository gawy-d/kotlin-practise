package gary.kotlinapp.home.view

import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @HomeScope
    fun presenter(): HomeContracts.Presenter =
        HomePresenter()

    @Provides
    @HomeScope
    fun router(): HomeContracts.Router =
        HomeRouter()

}