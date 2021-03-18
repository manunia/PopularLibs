package geekbrains.marial.app.mvp.presenter

import com.github.terrakok.cicerone.Router
import geekbrains.marial.app.mvp.model.entity.GithubUser
import geekbrains.marial.app.mvp.view.OneUserView
import moxy.MvpPresenter

class OneUserPresenter(val router: Router, val user: GithubUser) : MvpPresenter<OneUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user.login?.let{viewState.init(it)}
    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }

}
