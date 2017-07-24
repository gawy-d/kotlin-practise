package gary.kotlinapp.core.application.resource

import android.support.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes res: Int): String

}