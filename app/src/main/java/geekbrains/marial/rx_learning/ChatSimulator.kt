package geekbrains.marial.rx_learning

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.random.Random

interface IChat{
    interface IChatListener{
        fun onMessage(message: String)
        fun onConnect()
        fun onError(t: Throwable)
        fun onDisconnect()
    }

    fun setChatListener(listener: IChatListener)
    fun connect()
    fun disconnect()
}

class ChatImpl: IChat {
    var listener: IChat.IChatListener? = null
    var connected = false

    override fun setChatListener(listener: IChat.IChatListener) {
        this.listener = listener
    }

    private fun startMessages() {
        var i = 0
        while (connected) {
            Thread.sleep(Random.nextInt(1000,2000).toLong())
            val message = "message $i"
            println("Sending Message: $message on Thread: ${Thread.currentThread().name}")
            listener?.onMessage(message)
        }
    }

    override fun connect() {
        Schedulers.newThread().scheduleDirect {
            Thread.sleep(Random.nextInt(1000,2000).toLong())
            connected = true
            listener?.onConnect()

            startMessages()

        }
    }

    override fun disconnect() {
        connected = false
    }

}

class ChatRx(private val chat: IChat) {
    val chatObservble = Observable.create<String> { emitter ->
        chat.setChatListener(object : IChat.IChatListener {
            override fun onMessage(message: String) {
                emitter.onNext(message)
            }

            override fun onConnect() {
                emitter.onNext("Connected")
            }

            override fun onError(t: Throwable) {
                emitter.onError(t)
            }

            override fun onDisconnect() {
                emitter.onComplete()
            }

        })
    }.publish()

    fun connect() {
        chatObservble.connect()
        chat.connect()
    }

    fun disconnect() {
        chat.disconnect()
    }
}

class ChatConsumer() {
    private val chatRx = ChatRx(ChatImpl())

    fun run() {
        chatRx.connect()
        Schedulers.newThread().scheduleDirect {
            chatRx.chatObservble
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("Reciever message2: $it on Thread: ${Thread.currentThread().name}")
                },{
                    it.printStackTrace()
                })
        }
    }
}