package com.example.chatclient.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatclient.Model.ChatServerConnector
import com.example.chatclient.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectButton.setOnClickListener {
            ChatServerConnector.ipAddress = ipText.text.toString()
            Thread(ChatServerConnector).start()
            val intent = Intent(this, ChatActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}
