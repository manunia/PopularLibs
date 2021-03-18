package geekbrains.marial.app.mvp.presenter

import com.github.terrakok.cicerone.Router
import geekbrains.marial.app.mvp.model.GitHubUsersRepo
import geekbrains.marial.app.mvp.model.entity.GithubUser
import geekbrains.marial.app.mvp.presenter.list.IUsersListPresenter
import geekbrains.marial.app.mvp.view.OneUserView
import geekbrains.marial.app.mvp.view.UsersView
import geekbrains.marial.app.mvp.view.list.IUserItemView
import moxy.MvpPresenter

class OneUserPresenter(val router: Router, val user: GithubUser) : MvpPresenter<OneUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(user.login)
    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }

}
