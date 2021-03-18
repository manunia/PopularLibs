package geekbrains.marial.app.mvp.model.api

import geekbrains.marial.app.mvp.model.entity.GithubUser
import geekbrains.marial.app.mvp.model.entity.UserRepos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getReposUrl(@Url url: String): Single<List<UserRepos>>
}