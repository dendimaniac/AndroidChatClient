package com.example.chatclient.Model.Interfaces

import com.example.chatclient.Model.Data.ChatMessage

interface IObserver {
    fun newMessage(chatMessage: ChatMessage)
}