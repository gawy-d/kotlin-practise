package gary.kotlinapp.core.functions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle

/**
 * Create an intent targeting T class
 *
 * @param context The context
 * @param bundle Nullable Bundle as extras
 * @param data Nullable Uri as data
 * @param flags Nullable Int as flags (added, not set)
 */
inline fun <reified T> intent(
    context: Context,
    bundle: Bundle? = null,
    data: Uri? = null,
    flags: Int? = null
): Intent = Intent(context, T::class.java).apply {
    bundle?.let { this.putExtras(it) }
    data?.let { this.data = it }
    flags?.let { this.addFlags(it) }
}

/**
 * Start T activity
 *
 * @param activity The context activity
 * @param bundle Nullable Bundle as extras
 * @param data Nullable Uri as data
 * @param flags Nullable Int as flags (added, not set)
 * @param requestCode Nullable Int as request code. If not null, it will start T activity for result
 */
inline fun <reified T> startActivity(
    activity: Activity,
    bundle: Bundle? = null,
    data: Uri? = null,
    flags: Int? = null,
    requestCode: Int? = null
) = intent<T>(activity, bundle, data, flags).let {
    when (requestCode) {
        null -> activity.startActivity(it)
        else -> activity.startActivityForResult(it, requestCode)
    }
}