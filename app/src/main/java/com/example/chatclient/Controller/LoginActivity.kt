package com.example.chatclient.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chatclient.Model.*
import com.example.chatclient.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), IObserver {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ChatServerConnector.registerObserver(this)

        loginButton.setOnClickListener {
            ConnectorData.username = usernameText.text.toString()
            val loginMessage =
                ChatMessage(usernameText.text.toString(), Commands.Login, "", Time.getTime())
            Thread(ChatMessager(loginMessage)).start()
        }
    }

    override fun newMessage(chatMessage: ChatMessage) {
        if (chatMessage.command == Commands.Login) {
            runOnUiThread {
                usernameText.setText("")
                statusTextView.text = chatMessage.message
            }
        } else if (chatMessage.command == Commands.Chat) {
            if (chatMessage.username != ConnectorData.username) return

            val intent = Intent(this, ChatActivity::class.java).apply {}
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        ChatServerConnector.deregisterObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ChatServerConnector.quit()
    }
}
