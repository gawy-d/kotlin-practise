package gary.kotlinapp.core.view

import gary.kotlinapp.core.view.toolbar.ToolbarBuilder

interface ViewProvider {

    fun toolbarBuilder(): ToolbarBuilder

}