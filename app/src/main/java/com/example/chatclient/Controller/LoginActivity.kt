package com.example.chatclient.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatclient.Model.ChatConnector.ChatServerConnector
import com.example.chatclient.Model.Data.ChatMessage
import com.example.chatclient.Model.Data.Commands
import com.example.chatclient.Model.Data.ConnectorData
import com.example.chatclient.Model.Helpers.Time
import com.example.chatclient.Model.Interfaces.IObserver
import com.example.chatclient.Model.MessagesHandlers.ChatMessager
import com.example.chatclient.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), IObserver {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ChatServerConnector.registerObserver(this)

        loginButton.setOnClickListener {
            ConnectorData.username = topUsernameTextView.text.toString()
            val loginMessage =
                ChatMessage(
                    topUsernameTextView.text.toString(),
                    Commands.Login,
                    "",
                    Time.getTime()
                )
            Thread(ChatMessager(loginMessage)).start()
        }
    }

    override fun newMessage(chatMessage: ChatMessage) {
        if (chatMessage.command == Commands.Login) {
            runOnUiThread {
                topUsernameTextView.setText("")
                statusTextView.text = chatMessage.message
            }
        } else if (chatMessage.command == Commands.Chat) {
            if (chatMessage.username != ConnectorData.username) return

            val intent = Intent(this, MainActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}
