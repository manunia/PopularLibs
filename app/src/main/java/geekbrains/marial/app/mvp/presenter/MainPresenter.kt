package geekbrains.marial.app.mvp.presenter

import geekbrains.marial.app.mvp.model.CountersModel
import geekbrains.marial.app.mvp.view.MainView

class MainPresenter(val view: MainView) {

    val model = CountersModel()

    fun counterClick(index: Int) {
        val nextValue = model.next(index)
        view.setButtonText(index, nextValue.toString())

    }
}
