package com.example.chatclient.Model.MessagesHandlers

import com.example.chatclient.Model.ChatConnector.ChatServerConnector
import com.example.chatclient.Model.Data.ChatMessage
import kotlinx.serialization.json.Json
import java.util.*

class ChatListener(private val scanner: Scanner) : Runnable {
    override fun run() {
        while (true) {
            val newMessage = scanner.nextLine()
            val chatMessage = Json.parse(ChatMessage.serializer(), newMessage)
            ChatServerConnector.insert(chatMessage)
        }
    }
}