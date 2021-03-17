package geekbrains.marial.app.mvp.presenter

import com.github.terrakok.cicerone.Router
import geekbrains.marial.app.mvp.navigation.IScreens
import geekbrains.marial.app.mvp.view.MainView
import geekbrains.marial.rx_learning.BackPressure
import geekbrains.marial.rx_learning.ChatConsumer
import geekbrains.marial.rx_learning.Creation
import geekbrains.marial.rx_learning.Operator
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())

        //Creation().exec()
        //Operator().exec()
        //ChatConsumer().run()
        BackPressure().exec()
    }

    fun backClicked() {
        router.exit()
    }

}
