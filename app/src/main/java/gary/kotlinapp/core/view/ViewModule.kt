package gary.kotlinapp.core.view

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModule {

    @Provides
    @Singleton
    fun toolbarBuilder() = ToolbarBuilder()

}