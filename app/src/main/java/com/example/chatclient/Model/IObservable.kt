package com.example.chatclient.Model

interface IObservable {
    val observerSet: MutableSet<IObserver>

    fun registerObserver(newObserver: IObserver)
    fun deregisterObserver(targetObserver: IObserver)

    fun notifyObservers(message: ChatMessage)

    fun insert(message: ChatMessage)
}