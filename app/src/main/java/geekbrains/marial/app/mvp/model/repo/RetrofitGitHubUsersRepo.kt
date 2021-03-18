package geekbrains.marial.app.mvp.model.repo

import geekbrains.marial.app.mvp.model.api.IDataSource
import geekbrains.marial.app.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(val api: IDataSource): IGithubUsersRepo {

    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}