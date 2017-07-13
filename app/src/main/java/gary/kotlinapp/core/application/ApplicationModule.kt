package gary.kotlinapp.core.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun applicationContext(application: Application): Context = application.applicationContext


}