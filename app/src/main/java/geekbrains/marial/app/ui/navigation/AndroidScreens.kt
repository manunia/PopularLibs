package geekbrains.marial.app.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import geekbrains.marial.app.mvp.model.entity.GithubUser
import geekbrains.marial.app.mvp.navigation.IScreens
import geekbrains.marial.app.ui.fragment.OneUserFragment
import geekbrains.marial.app.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun oneUser(user: GithubUser)= FragmentScreen {
        OneUserFragment.newInstance()
    }
}