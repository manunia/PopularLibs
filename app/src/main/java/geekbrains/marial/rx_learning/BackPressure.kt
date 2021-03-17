package geekbrains.marial.rx_learning

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class BackPressure {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {

        fun noBackPressure() = Observable.range(0, 1000000).subscribeOn(Schedulers.io())
        fun backPressure() = Flowable.range(0, 1000000).subscribeOn(Schedulers.io()).onBackpressureLatest()
    }

    class Consumer(val producer: Producer) {

        fun exec() {
            consumeNoBackPressure()
        }

        fun consumeNoBackPressure() {
            producer.noBackPressure()
                .observeOn(Schedulers.computation())
                .subscribe({
                    Thread.sleep(1000)
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun consumeBackPressure() {
            producer.backPressure()
                .observeOn(Schedulers.computation(),false,1)
                .subscribe({
                    Thread.sleep(1000)
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}
