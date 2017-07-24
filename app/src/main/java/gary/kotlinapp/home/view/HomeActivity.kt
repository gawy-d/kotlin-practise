package gary.kotlinapp.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gary.kotlinapp.R
import gary.kotlinapp.application.KotlinApplication
import gary.kotlinapp.core.view.ToolbarBuilder
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContracts.View {

    @Inject internal lateinit var presenter: HomeContracts.Presenter
    @Inject internal lateinit var router: HomeContracts.Router
    @Inject internal lateinit var toolbarBuilder: ToolbarBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        KotlinApplication.componentsHolder.homeComponentHolder.get().inject(this)

        router.onCreate(this)
        presenter.onCreate(this, router)

        init()
    }

    private fun init() {
        toolbarBuilder.build(this, toolbar, getString(R.string.title_home))

        twitchCTA.setOnClickListener { presenter.onTwitchCTAClicked() }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        router.onDestroy()

        KotlinApplication.componentsHolder.homeComponentHolder.release()

        super.onDestroy()
    }

}
