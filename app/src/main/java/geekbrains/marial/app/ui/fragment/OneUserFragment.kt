package geekbrains.marial.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import geekbrains.marial.app.databinding.FragmentOneUserBinding
import geekbrains.marial.app.mvp.model.entity.GithubUser
import geekbrains.marial.app.mvp.presenter.OneUserPresenter
import geekbrains.marial.app.mvp.view.OneUserView
import geekbrains.marial.app.ui.App
import geekbrains.marial.app.ui.BackClickListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class OneUserFragment() : MvpAppCompatFragment(), OneUserView, BackClickListener {

    companion object {
        private const val USER = "user"
        fun newInstance(user: GithubUser) = OneUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        OneUserPresenter(App.instance.router, user)
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

    override fun init(login: String) {
        vb?.tvUserLogin?.text = login
    }

    override fun backPressed() = presenter.backClick()

}