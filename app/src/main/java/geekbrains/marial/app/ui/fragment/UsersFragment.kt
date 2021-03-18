package geekbrains.marial.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.marial.app.databinding.FragmentUsersBinding
import geekbrains.marial.app.mvp.model.api.ApiHolder
import geekbrains.marial.app.mvp.model.repo.RetrofitGitHubUsersRepo
import geekbrains.marial.app.mvp.presenter.UsersPresenter
import geekbrains.marial.app.mvp.view.UsersView
import geekbrains.marial.app.ui.App
import geekbrains.marial.app.ui.BackClickListener
import geekbrains.marial.app.ui.adapter.UsersRVAdapter
import geekbrains.marial.app.ui.image.GlideImageLoader
import geekbrains.marial.app.ui.navigation.AndroidScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread(),
            RetrofitGitHubUsersRepo(ApiHolder.api),
            App.instance.router,
            AndroidScreens())
    }

    private var vb: FragmentUsersBinding? = null
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}