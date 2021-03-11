package geekbrains.marial.app.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import geekbrains.marial.app.mvp.navigation.IScreens
import geekbrains.marial.app.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}