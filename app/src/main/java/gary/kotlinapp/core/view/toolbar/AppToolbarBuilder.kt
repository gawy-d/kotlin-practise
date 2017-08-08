package gary.kotlinapp.core.view.toolbar

import android.support.v7.app.AppCompatActivity
import gary.kotlinapp.R

internal class AppToolbarBuilder : ToolbarBuilder {

    private var activity: AppCompatActivity? = null

    override fun create(
        activity: AppCompatActivity
    ) {
        this.activity = activity
    }

    override fun destroy() {
        activity = null
    }

    override fun build(
        title: String?,
        subtitle: String?,
        displayHomeAsUp: Boolean,
        indicator: Int
    ) = activity?.let {
        it.setSupportActionBar(it.findViewById(R.id.toolbar))
        it.supportActionBar?.let {
            it.title = title
            it.subtitle = subtitle
            it.setDisplayHomeAsUpEnabled(displayHomeAsUp)
            it.setHomeAsUpIndicator(indicator)
        }
    } ?: Unit
}