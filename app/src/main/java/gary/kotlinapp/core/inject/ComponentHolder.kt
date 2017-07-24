package gary.kotlinapp.core.inject

interface ComponentHolder<out T> {

    fun get(): T

    fun release()

}