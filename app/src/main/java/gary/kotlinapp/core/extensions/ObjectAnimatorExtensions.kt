package gary.kotlinapp.core.extensions

import android.animation.ObjectAnimator

fun ObjectAnimator.startIfNotRunning() =
    if (this.isRunning) {
        this.start()
    } else {
        Unit
    }