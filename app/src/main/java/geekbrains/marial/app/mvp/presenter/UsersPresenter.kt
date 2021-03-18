package geekbrains.marial.app.mvp.presenter

import com.github.terrakok.cicerone.Router
import geekbrains.marial.app.mvp.model.entity.GithubUser
import geekbrains.marial.app.mvp.model.repo.IGithubUsersRepo
import geekbrains.marial.app.mvp.navigation.IScreens
import geekbrains.marial.app.mvp.presenter.list.IUsersListPresenter
import geekbrains.marial.app.mvp.view.UsersView
import geekbrains.marial.app.mvp.view.list.IUserItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(
    val uiSheduler: Scheduler,
    val repo: IGithubUsersRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.loadAvatar(user.avatarUrl)
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
        repo.getUsers()
            .observeOn(uiSheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.printStackTrace()}")
            })

    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}
