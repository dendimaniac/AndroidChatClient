package com.example.chatclient.Model.Data

object ConnectorData{
    var username = ""
    val messageList: MutableList<ChatMessage> = mutableListOf()
    var canChat = false
    val topChatterMap: MutableMap<String, Int> = mutableMapOf()
}
