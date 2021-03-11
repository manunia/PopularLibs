package geekbrains.marial.app.mvp.model

import geekbrains.marial.app.mvp.model.entity.GithubUser

class GitHubUsersRepo {
    private val users = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
        GithubUser("login7")
    )

    fun getUsers(): List<GithubUser> {
        return users;
    }
}