package geekbrains.marial.app.mvp.presenter

import geekbrains.marial.app.mvp.model.CountersModel
import geekbrains.marial.app.mvp.view.MainView

class MainPresenter(val view: MainView) {

    val model = CountersModel()

    fun counterBtn1Click() {
        val nextValue = model.next(0)
        view.setButton1Text(nextValue.toString())
    }

    fun counterBtn2Click() {
        val nextValue = model.next(1)
        view.setButton2Text(nextValue.toString())
    }

    fun counterBtn3Click() {
        val nextValue = model.next(2)
        view.setButton3Text(nextValue.toString())
    }
}
