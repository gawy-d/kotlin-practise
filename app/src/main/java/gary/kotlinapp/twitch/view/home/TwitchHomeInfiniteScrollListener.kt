package gary.kotlinapp.twitch.view.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class TwitchHomeInfiniteScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener(), TwitchHomeContracts.View.OnScrollListener {

    private val THRESHOLD = 5

    private var loading = true
    private var previousTotal = 0
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    private var loadMoreData: (() -> Unit)? = null

    override fun setLoadMoreDataAction(
        loadMoreData: () -> Unit
    ) {
        this.loadMoreData = loadMoreData
    }

    override fun reset() {
        loading = true
        previousTotal = 0
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
    }

    override fun onScrolled(
        recyclerView: RecyclerView?,
        dx: Int,
        dy: Int
    ) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) {
            return
        }

        visibleItemCount = layoutManager.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading && totalItemCount > previousTotal) {
            loading = false
            previousTotal = totalItemCount
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + THRESHOLD)) {
            loading = true
            loadMoreData?.invoke()
        }
    }
}