package com.example.chatclient.Model.MessagesHandlers

import com.example.chatclient.Model.Data.ChatMessage
import com.example.chatclient.Model.Data.Commands
import com.example.chatclient.Model.Data.ConnectorData
import kotlinx.serialization.json.Json

object CommandsHandler {
    fun checkCommand(chatMessage: ChatMessage) {
        when (chatMessage.command) {
            Commands.Chat -> {
                if (ConnectorData.canChat) {
                    ConnectorData.messageList.add(chatMessage)
                }
                val requestTopMessage =
                    ChatMessage(ConnectorData.username, Commands.Top, "", "")
                Thread(ChatMessager(requestTopMessage)).start()
            }
            Commands.History -> {
                if (chatMessage.username != ConnectorData.username) return
                val historyList = chatMessage.message.split("|")
                val historyMessages: MutableList<ChatMessage> = mutableListOf()
                historyList.forEach {
                    val singleMessageHistory = Json.parse(ChatMessage.serializer(), it)
                    historyMessages.add(singleMessageHistory)
                }
                ConnectorData.messageList.clear()
                ConnectorData.messageList.addAll(historyMessages)
            }
            Commands.Top -> {
                if (chatMessage.username != ConnectorData.username) return
                val topList = chatMessage.message.split("|")
                val topMessages: MutableMap<String, Int> = mutableMapOf()
                topList.forEach {
                    val singleTop = Json.parse(ChatMessage.serializer(), it)
                    topMessages[singleTop.username] = singleTop.message.toInt()
                }
                ConnectorData.topChatterMap.clear()
                ConnectorData.topChatterMap.putAll(topMessages)
            }
        }
    }
}