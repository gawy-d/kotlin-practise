package gary.kotlinapp.core.application.resource

import android.support.annotation.StringRes

interface ResourceProvider {

    /**
     * Get a string resource
     *
     * @param res The string resource id
     * @return The string resource
     */
    fun getString(
        @StringRes res: Int
    ): String

}