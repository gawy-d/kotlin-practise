package gary.kotlinapp.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.textChanges
import gary.kotlinapp.R
import gary.kotlinapp.application.KotlinApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContracts.View {

    @Inject internal lateinit var presenter: HomeContracts.Presenter
    @Inject internal lateinit var interactor: HomeContracts.Interactor

    private lateinit var homeComponentHolder: HomeComponentHolder

    private val progressAnimation: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(progressBar, "alpha", 0f, 1f).setDuration(250)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                true
            }
            R.id.navigation_dashboard -> {
                true
            }
            R.id.navigation_notifications -> {
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeComponentHolder = KotlinApplication.componentsHolder.home
        homeComponentHolder.get().inject(this)

        bottomNavigationBar.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        presenter.onCreate(this, interactor, HomeRouter(this))
        presenter.init(queryEditText.textChanges())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        homeComponentHolder.release()

        super.onDestroy()
    }

    override fun displayChannels(channels: String) {
        channelsText.text = channels
    }

    override fun hideChannels() {
        channelsText.text = null
    }

    override fun displayError(error: String) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun displayLoader() {
        progressBar.alpha = 1f
    }

    override fun hideLoader() {
        progressBar.alpha = 0f
    }

    override fun displayLoaderWithAnimation() {
        if (!progressAnimation.isRunning) {
            progressAnimation.start()
        }
    }

    override fun hideLoaderWithAnimation() {
        progressAnimation.reverse()
    }
}
