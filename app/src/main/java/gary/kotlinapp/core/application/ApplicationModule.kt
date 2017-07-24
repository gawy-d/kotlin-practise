package gary.kotlinapp.core.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import gary.kotlinapp.core.application.resource.AppResourceProvider
import gary.kotlinapp.core.application.resource.ResourceProvider
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun applicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun resourceProvider(@ApplicationContext context: Context): ResourceProvider =
        AppResourceProvider(context)

}