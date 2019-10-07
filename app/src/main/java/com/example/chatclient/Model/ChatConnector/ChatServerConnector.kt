package com.example.chatclient.Model.ChatConnector

import com.example.chatclient.Model.Data.ChatMessage
import com.example.chatclient.Model.Data.Commands
import com.example.chatclient.Model.Data.ConnectorData
import com.example.chatclient.Model.Interfaces.IObservable
import com.example.chatclient.Model.Interfaces.IObserver
import com.example.chatclient.Model.MessagesHandlers.ChatListener
import com.example.chatclient.Model.MessagesHandlers.CommandsHandler
import java.io.PrintStream
import java.net.Socket
import java.util.*

object ChatServerConnector : IObservable, Runnable {
    override val observerSet: MutableSet<IObserver> = mutableSetOf()

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
        CommandsHandler.checkCommand(message)
        notifyObservers(message)
    }

    override fun run() {
        socket = Socket(
            ipAddress,
            port
        )

        scanner = Scanner(socket.getInputStream())
        Thread(ChatListener(scanner)).start()

        printStream = PrintStream(socket.getOutputStream(), true)
    }

    fun quit() {
        socket.close()
    }
}