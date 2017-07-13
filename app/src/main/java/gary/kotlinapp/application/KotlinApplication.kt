package gary.kotlinapp.application

import android.app.Application
import com.facebook.stetho.Stetho

class KotlinApplication : Application() {

    private val component: KotlinApplicationComponent by lazy {
        DaggerKotlinApplicationComponent.builder().kotlinApplicationModule(KotlinApplicationModule(this)).build()
    }

    companion object {
        lateinit var componentsHolder: KotlinApplicationComponentHolder
    }

    override fun onCreate() {
        super.onCreate()

        componentsHolder = KotlinApplicationComponentHolder(component)

        Stetho.initializeWithDefaults(this)
    }
}