package geekbrains.marial.rx_learning

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.lang.RuntimeException
import kotlin.random.Random

class Sources {

    val observer = object : Observer<String> {
        override fun onSubscribe(d: Disposable?) {
            TODO("Not yet implemented")
        }

        override fun onNext(t: String?) {
            TODO("Not yet implemented")
        }

        override fun onError(e: Throwable?) {
            TODO("Not yet implemented")
        }

        override fun onComplete() {
            TODO("Not yet implemented")
        }

    }

    val singleObserver = object : SingleObserver<String> {
        override fun onSubscribe(d: Disposable?) {
            TODO("Not yet implemented")
        }

        override fun onSuccess(t: String?) {
            TODO("Not yet implemented")
        }

        override fun onError(e: Throwable?) {
            TODO("Not yet implemented")
        }

    }

    val maybeObserver = object : MaybeObserver<String> {
        override fun onSubscribe(d: Disposable?) {
            TODO("Not yet implemented")
        }

        override fun onSuccess(t: String?) {
            TODO("Not yet implemented")
        }

        override fun onError(e: Throwable?) {
            TODO("Not yet implemented")
        }

        override fun onComplete() {
            TODO("Not yet implemented")
        }
    }

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {

        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun single() = Single.fromCallable {
            return@fromCallable "1"
        }

        fun maybe() = Maybe.create<Boolean> { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onSuccess(it)
                } else {
                    emitter.onComplete()
                }
            }
        }

        fun completable() = Completable.create { emitter ->
            randomResultOperation().let {
                randomResultOperation().let {
                    if (it) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(RuntimeException("Error"))
                    }
                }
            }
        }

        fun publishSubject() = PublishSubject.create<String>().apply {
            Completable.fromAction {
                Thread.sleep(1000)
                onNext("Hello from subject")
            }
        }.subscribeOn(Schedulers.computation())

    }

    class Consumer(val producer: Producer) {

        fun exec() {

        }


    }
}