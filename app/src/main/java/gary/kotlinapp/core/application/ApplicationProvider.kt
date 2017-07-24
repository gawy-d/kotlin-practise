package gary.kotlinapp.core.application

import android.content.Context
import gary.kotlinapp.core.application.resource.ResourceProvider

interface ApplicationProvider {

    @ApplicationContext
    fun applicationContext(): Context

    fun resourceProvider(): ResourceProvider

}