package com.example.chatclient.Model.MessagesHandlers

import com.example.chatclient.Model.ChatConnector.ChatServerConnector
import com.example.chatclient.Model.Data.ChatMessage
import kotlinx.serialization.json.Json

class ChatMessager(private val chatMessage: ChatMessage) : Runnable {
    override fun run() {
        val newMessage = Json.stringify(ChatMessage.serializer(), chatMessage)
        ChatServerConnector.printStream.println(newMessage)
    }
}