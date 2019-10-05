package com.example.chatclient.Model

import kotlinx.serialization.json.Json

class ChatMessager(private val chatMessage: ChatMessage) : Runnable {
    override fun run() {
        val newMessage = Json.stringify(ChatMessage.serializer(), chatMessage)
        ChatServerConnector.printStream.println(newMessage)
    }
}