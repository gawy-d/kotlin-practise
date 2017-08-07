package gary.kotlinapp.core.view.toolbar

import android.support.v7.app.AppCompatActivity

interface ToolbarBuilder {

    fun create(activity: AppCompatActivity)

    fun destroy()

    fun build(
        title: String? = null,
        subtitle: String? = null,
        displayHomeAsUp: Boolean = false,
        displayShowHome: Boolean = false,
        indicator: Int = 0
    )

}