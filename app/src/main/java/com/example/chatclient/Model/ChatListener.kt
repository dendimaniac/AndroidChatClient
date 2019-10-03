package com.example.chatclient.Model

import kotlinx.serialization.json.Json
import java.util.*

class ChatListener(val scanner: Scanner) : Runnable {
    override fun run() {
        while (true) {
            val newMessage = scanner.nextLine()
            val chatMessage = Json.parse(ChatMessage.serializer(), newMessage)
            ChatServerConnector.insert(chatMessage)
        }
    }
}