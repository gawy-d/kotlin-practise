package gary.kotlinapp.core.application.resource

import android.content.Context
import android.support.annotation.StringRes

class AppResourceProvider constructor(
    private val context: Context
) : ResourceProvider {

    override fun getString(
        @StringRes res: Int
    ): String = context.getString(res)

}