package geekbrains.marial.app.mvp.presenter

import com.github.terrakok.cicerone.Router
import geekbrains.marial.app.mvp.model.GitHubUsersRepo
import geekbrains.marial.app.mvp.model.entity.GithubUser
import geekbrains.marial.app.mvp.navigation.IScreens
import geekbrains.marial.app.mvp.presenter.list.IUsersListPresenter
import geekbrains.marial.app.mvp.view.UsersView
import geekbrains.marial.app.mvp.view.list.IUserItemView
import moxy.MvpPresenter

class UsersPresenter(val repo: GitHubUsersRepo, val router: Router, val screens: IScreens) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(screens.oneUser(user))
        }

    }

    fun loadData() {
        val users = repo.getUsers().subscribe{
            println(it)
            usersListPresenter.users.add(it)
        }
        //usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}
