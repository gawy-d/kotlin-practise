package gary.kotlinapp.home.view

import dagger.Module
import dagger.Provides
import gary.kotlinapp.core.application.resource.ResourceProvider

@Module
class HomeModule {

    @Provides
    @HomeScope
    fun presenter(
        resourceProvider: ResourceProvider
    ): HomeContracts.Presenter = HomePresenter(resourceProvider)

    @Provides
    @HomeScope
    fun router(): HomeContracts.Router = HomeRouter()

}