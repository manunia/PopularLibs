package geekbrains.marial.app.mvp.navigation

import com.github.terrakok.cicerone.Screen
import geekbrains.marial.app.mvp.model.entity.GithubUser

interface IScreens {
    fun users(): Screen
    fun oneUser(user: GithubUser): Screen
}