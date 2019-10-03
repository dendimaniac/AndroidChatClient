package com.example.chatclient.Model

interface IObserver {
    fun newMessage(chatMessage: ChatMessage)
}