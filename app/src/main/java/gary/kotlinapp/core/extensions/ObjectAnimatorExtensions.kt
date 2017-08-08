package gary.kotlinapp.core.extensions

import android.animation.ObjectAnimator

/**
 * Start an animation only if it's not currently running
 */
fun ObjectAnimator.startIf(condition: Boolean) = if (condition) this.start() else Unit

fun ObjectAnimator.startIf(condition: () -> Boolean) = if (condition()) this.start() else Unit

fun ObjectAnimator.reverseIf(condition: Boolean) = if (condition) this.reverse() else Unit

fun ObjectAnimator.reverseIf(condition: () -> Boolean) = if (condition()) this.reverse() else Unit
