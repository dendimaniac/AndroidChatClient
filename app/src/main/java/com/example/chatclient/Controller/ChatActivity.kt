package com.example.chatclient.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatclient.Model.ChatMessage
import com.example.chatclient.Model.ChatServerConnector
import com.example.chatclient.Model.ConnectorData
import com.example.chatclient.Model.IObserver
import com.example.chatclient.R
import kotlinx.android.synthetic.main.activity_chat.*
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ChatActivity : AppCompatActivity(), IObserver {
    private val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        ChatServerConnector.registerObserver(this)

        sendButton.setOnClickListener {
            val currentTime = Instant.now()
            val timeStamp = formatter.format(currentTime)

            val chatMessage = ChatMessage(messageInput.text.toString(), ConnectorData.username, timeStamp)
            ChatServerConnector.insert(chatMessage)
        }
    }

    override fun newMessage(chatMessage: ChatMessage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
