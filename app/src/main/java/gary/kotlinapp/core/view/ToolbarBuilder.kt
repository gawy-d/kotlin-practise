package gary.kotlinapp.core.view

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import javax.inject.Inject

class ToolbarBuilder @Inject constructor() {

    fun build(
        activity: AppCompatActivity,
        toolbar: Toolbar,
        title: String? = null,
        subtitle: String? = null,
        displayHomeAsUp: Boolean = false,
        displayShowHome: Boolean = false,
        indicator: Int = 0
    ) {
        activity.setSupportActionBar(toolbar)

        val actionBar = activity.supportActionBar!!
        actionBar.title = title
        actionBar.subtitle = subtitle
        actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUp)
        actionBar.setDisplayShowHomeEnabled(displayShowHome)
        actionBar.setHomeAsUpIndicator(indicator)
    }

}