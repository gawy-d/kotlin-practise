package gary.kotlinapp.application

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class KotlinApplicationModule(
    private val application: Application
) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

}