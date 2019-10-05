package com.example.chatclient.Model

import android.util.Log
import java.io.PrintStream
import java.lang.Exception
import java.net.Socket
import java.util.*

object ChatServerConnector : IObservable, Runnable {
    override val observerSet: MutableSet<IObserver> = mutableSetOf()
    private val messageList: MutableList<ChatMessage> = mutableListOf()

    lateinit var socket: Socket

    lateinit var scanner: Scanner
    lateinit var printStream: PrintStream

    var ipAddress: String = ""
    const val port = 23

    override fun registerObserver(newObserver: IObserver) {
        observerSet.add(newObserver)
    }

    override fun deregisterObserver(targetObserver: IObserver) {
        observerSet.remove(targetObserver)
    }

    override fun notifyObservers(message: ChatMessage) {
        observerSet.forEach {
            it.newMessage(message)
        }
    }

    override fun insert(message: ChatMessage) {
        messageList.add(message)
        notifyObservers(message)
    }

    override fun run() {
        socket = Socket(ipAddress, port)

        scanner = Scanner(socket.getInputStream())
        Thread(ChatListener(scanner)).start()

        printStream = PrintStream(socket.getOutputStream(), true)
    }
}