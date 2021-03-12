package geekbrains.marial.app.mvp.model

class CountersModel {

    var countres = mutableListOf(0, 0, 0)

    fun getCurrent(index: Int) = countres[index]

    fun next(index: Int): Int {
        countres[index]++
        return getCurrent(index)
    }
}