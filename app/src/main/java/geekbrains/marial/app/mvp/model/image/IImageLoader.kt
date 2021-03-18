package geekbrains.marial.app.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}