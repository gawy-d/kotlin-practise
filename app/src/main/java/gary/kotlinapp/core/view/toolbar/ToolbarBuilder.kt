package gary.kotlinapp.core.view.toolbar

import android.support.v7.app.AppCompatActivity

interface ToolbarBuilder {

    /**
     * Create the builder with host activity
     *
     * @param activity The host activity
     */
    fun create(
        activity: AppCompatActivity
    )

    /**
     * Destroy the builder
     */
    fun destroy()

    /**
     * Build the toolbar
     *
     * @param title Nullable string as title
     * @param subtitle Nullable string as subtitle
     * @param displayHomeAsUp True if the arrow button is visible
     */
    fun build(
        title: String? = null,
        subtitle: String? = null,
        displayHomeAsUp: Boolean = false,
        indicator: Int = 0
    )

}