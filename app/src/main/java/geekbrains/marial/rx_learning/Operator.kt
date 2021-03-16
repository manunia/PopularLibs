package geekbrains.marial.rx_learning

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Operator {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {

        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun just(): Observable<String> = Observable.just("1", "2", "3","5","6","7")
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
        }.subscribeOn(Schedulers.newThread())
    }

    class Consumer(val producer: Producer) {

        fun exec() {
            //execMap()
            //execFlatMap()//onNext: [3x, 2x, 1x, 6x, 5x, 7x]
            execSwitchMap()//onNext: [7switch]
        }

        fun execMap() {
            producer.fromIterable().map {
                it + it
            }.subscribe {
                println(it)
            }
        }

        fun execFilter() {
            producer.fromIterable().filter {
                it.toInt() % 2 == 0
            }.subscribe {
                println(it)
            }
        }

        fun execDistinct() {
            producer.fromIterable().distinct()
                .subscribe {
                    println(it)
                }
        }

        fun execTake() {
            producer.fromIterable()
                .take(2)
                .subscribe{
                    println(it)
                }
        }

        fun execSkip() {
            producer.fromIterable()
                .skip(2)
                .subscribe{
                    println(it)
                }
        }

        fun execMerge() {
            producer.fromIterable()
                .mergeWith(producer.just())
                .subscribe{
                    println(it)
                }
        }

        fun execDebounce() {
            Observable.create<String> { emitter ->
                Thread.sleep(100)
                emitter.onNext("1")
                Thread.sleep(200)
                emitter.onNext("2")
                Thread.sleep(300)
                emitter.onNext("3")
                Thread.sleep(400)
                emitter.onNext("4")
            }.debounce(300, TimeUnit.MILLISECONDS)
                .subscribe {
                    println(it)
                }
        }

        fun execZip() {
            val observable1 = Observable.just("1").delay(1, TimeUnit.SECONDS).doOnNext{ println("1 emitted")}
            val observable2 = Observable.just("2").delay(2, TimeUnit.SECONDS).doOnNext{ println("2 emitted")}
            val observable3 = Observable.just("3").delay(3, TimeUnit.SECONDS).doOnNext{ println("3 emitted")}
            val observable4 = Observable.just("4").delay(4, TimeUnit.SECONDS).doOnNext{ println("4 emitted")}

            Observable.zip(observable1,observable2,observable3,observable4,{t1,t2,t3,t4 ->
                return@zip listOf(t1,t2,t3,t4)
            }).subscribe{
                println("Zip: $it")
            }
        }

        fun execFlatMap() {
            val testScheduler = TestScheduler()
            producer.just()
                .flatMap {
                    val delay = Random.nextInt(10).toLong()
                    return@flatMap Observable.just(it + "x").delay(delay,TimeUnit.SECONDS,testScheduler)
                }
                .toList()
                .subscribe({ s->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
            testScheduler.advanceTimeBy(1,TimeUnit.MINUTES)
//            Observable.just(listOf("1","2","3"))
//                .flatMap{ list ->
//                    Observable.fromIterable(list)
//                }.subscribe{
//
//                }
        }

        fun execSwitchMap() {
            val testScheduler = TestScheduler()
            producer.just()
                .switchMap {
                    val delay = Random.nextInt(10).toLong()
                    return@switchMap Observable.just(it + "switch").delay(delay,TimeUnit.SECONDS,testScheduler)
                }
                .toList()
                .subscribe({ s->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
            testScheduler.advanceTimeBy(1,TimeUnit.MINUTES)
        }
    }
}