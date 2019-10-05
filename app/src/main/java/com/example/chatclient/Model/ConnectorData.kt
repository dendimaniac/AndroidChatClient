package com.example.chatclient.Model

object ConnectorData : IObserver {
    var username = ""
    val messageList: MutableList<ChatMessage> = mutableListOf()

    init {
        ChatServerConnector.registerObserver(this)
    }

    override fun newMessage(chatMessage: ChatMessage) {
        messageList.add(chatMessage)
    }
}
