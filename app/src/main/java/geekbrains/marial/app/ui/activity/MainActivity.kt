package geekbrains.marial.app.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import geekbrains.marial.app.R
import geekbrains.marial.app.databinding.ActivityMainBinding
import geekbrains.marial.app.mvp.model.GitHubUsersRepo
import geekbrains.marial.app.mvp.presenter.MainPresenter
import geekbrains.marial.app.mvp.view.MainView
import geekbrains.marial.app.ui.App
import geekbrains.marial.app.ui.adapter.UsersRVAdapter
import geekbrains.marial.app.ui.navigation.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

}