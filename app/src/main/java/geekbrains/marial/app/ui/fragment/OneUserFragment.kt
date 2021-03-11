package geekbrains.marial.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.marial.app.databinding.FragmentOneUserBinding
import geekbrains.marial.app.databinding.FragmentUsersBinding
import geekbrains.marial.app.mvp.model.GitHubUsersRepo
import geekbrains.marial.app.mvp.presenter.OneUserPresenter
import geekbrains.marial.app.mvp.presenter.UsersPresenter
import geekbrains.marial.app.mvp.view.OneUserView
import geekbrains.marial.app.mvp.view.UsersView
import geekbrains.marial.app.ui.App
import geekbrains.marial.app.ui.BackClickListener
import geekbrains.marial.app.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class OneUserFragment : MvpAppCompatFragment(), OneUserView, BackClickListener {

    companion object {
        fun newInstance() = OneUserFragment()
    }

    private val presenter by moxyPresenter {
        OneUserPresenter(GitHubUsersRepo(), App.instance.router)
    }

    private var vb: FragmentOneUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentOneUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.tvUserLogin?.text = "test"
    }

    override fun backPressed() = presenter.backClick()

}