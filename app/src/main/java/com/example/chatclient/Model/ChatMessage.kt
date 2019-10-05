package com.example.chatclient.Model

import kotlinx.serialization.Serializable

@Serializable
class ChatMessage(val message: String, val username: String, val timestamp: String) {
    override fun toString(): String {
        return "$timestamp, $username: $message"
    }
}