package gary.kotlinapp.core.view.toolbar

import android.support.v7.app.AppCompatActivity
import gary.kotlinapp.R

internal class AppToolbarBuilder : ToolbarBuilder {

    private var activity: AppCompatActivity? = null

    override fun create(activity: AppCompatActivity) {
        this.activity = activity
    }

    override fun destroy() {
        activity = null
    }

    override fun build(
        title: String?,
        subtitle: String?,
        displayHomeAsUp: Boolean,
        displayShowHome: Boolean,
        indicator: Int
    ) {
        activity?.apply {
            setSupportActionBar(findViewById(R.id.toolbar))

            supportActionBar?.let {
                it.title = title
                it.subtitle = subtitle
                it.setDisplayHomeAsUpEnabled(displayHomeAsUp)
                it.setDisplayShowHomeEnabled(displayShowHome)
                it.setHomeAsUpIndicator(indicator)
            }
        }
    }

}