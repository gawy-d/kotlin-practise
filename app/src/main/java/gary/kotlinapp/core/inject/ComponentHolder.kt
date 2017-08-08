package gary.kotlinapp.core.inject

interface ComponentHolder<out T> {

    /**
     * Get the component inside the holder
     *
     * @return The component
     */
    fun get(): T

    /**
     * Release the component inside the holder
     */
    fun release()

}