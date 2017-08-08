package gary.kotlinapp.core.view

import dagger.Module
import dagger.Provides
import gary.kotlinapp.core.view.toolbar.AppToolbarBuilder
import gary.kotlinapp.core.view.toolbar.ToolbarBuilder

@Module
class ViewModule {

    @Provides
    fun toolbarBuilder(): ToolbarBuilder = AppToolbarBuilder()

}