package gary.kotlinapp.twitch.view.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.jakewharton.rxbinding2.widget.textChanges
import gary.kotlinapp.R
import gary.kotlinapp.application.KotlinApplication
import gary.kotlinapp.core.view.ToolbarBuilder
import gary.kotlinapp.twitch.view.home.channel.item.TwitchHomeChannelItemAdapter
import kotlinx.android.synthetic.main.activity_twitch_home.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

class TwitchHomeActivity : AppCompatActivity(), TwitchHomeContracts.View {

    @Inject internal lateinit var interactor: TwitchHomeContracts.Interactor
    @Inject internal lateinit var presenter: TwitchHomeContracts.Presenter
    @Inject internal lateinit var router: TwitchHomeContracts.Router
    @Inject internal lateinit var listAdapter: TwitchHomeChannelItemAdapter
    @Inject internal lateinit var toolbarBuilder: ToolbarBuilder

    private val progressAnimation: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(progressBar, "alpha", 0f, 1f).setDuration(250)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitch_home)

        KotlinApplication.componentsHolder.twitchComponentHolder.getHomeComponentHolder().get().inject(this)

        toolbarBuilder.build(this, toolbar, getString(R.string.title_twitch_home), null, true)

        val layoutManager = LinearLayoutManager(this)
        val scrollListener = TwitchHomeInfiniteScrollListener(layoutManager)

        channelsRecyclerView.layoutManager = layoutManager
        channelsRecyclerView.adapter = listAdapter
        channelsRecyclerView.addOnScrollListener(scrollListener)

        router.onCreate(this)
        presenter.onCreate(this, listAdapter, scrollListener, interactor, router)
        presenter.init(queryEditText.textChanges())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        router.onDestroy()

        KotlinApplication.componentsHolder.twitchComponentHolder.release()

        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun displayError(error: String) =
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show()

    override fun displayLoaderWithAnimation() {
        if (!progressAnimation.isRunning) {
            progressAnimation.start()
        }
    }

    override fun hideLoaderWithAnimation() =
        progressAnimation.reverse()
}
