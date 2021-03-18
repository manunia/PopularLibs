package geekbrains.marial.rx_learning

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Creation {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {

        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun just(): Observable<String> = Observable.just("1", "2", "3")
        fun fromIterable() = Observable.fromIterable(listOf("3", "4", "5"))
        fun range() = Observable.range(0, 10)
        fun interval() = Observable.interval(1, TimeUnit.SECONDS)
        fun fromCallable() = Observable.fromCallable {
            val result = randomResultOperation()
            return@fromCallable "Result long operation $result"
        }.subscribeOn(Schedulers.io())

        fun create() = Observable.create<String> {emitter ->
            for (i in 0..10) {
                randomResultOperation().let {
                    if (it) {
                        emitter.onNext("success")
                    } else {
                        emitter.onError(RuntimeException("Error"))
                    }
                }
            }

            emitter.onComplete()
        }
    }

    class Consumer(val producer: Producer) {

        val stringObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                println("onSubscribe")
            }

            override fun onNext(s: String?) {
                println("onNext: $s")
            }

            override fun onError(e: Throwable) {
                println("onError")
                e.printStackTrace()
            }

            override fun onComplete() {
                println("onComplete")
            }

        }

        fun exec() {
            //execJust()
            //execFromIterable()
            execFromCallable()
        }

        fun execJust() {
            val observable = producer.just()
            observable.subscribe(stringObserver)
        }

        fun execFromIterable() {
            val observable = producer.fromIterable().subscribe(stringObserver)
        }

        fun execInterval() {
            val disposable = producer.interval().subscribe({
                println("onNext: $it")
            }, {
                println("onError")
            }, {

            })

            //disposable.dispose()
        }

        fun execFromCallable() {
            producer.fromCallable().subscribe({
                println("onNext: $it")
            }, {
                println("onError")
            })
        }
    }
}