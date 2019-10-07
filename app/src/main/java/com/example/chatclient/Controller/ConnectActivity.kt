package com.example.chatclient.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatclient.Model.ChatConnector.ChatServerConnector
import com.example.chatclient.R
import kotlinx.android.synthetic.main.activity_connect.*

class ConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)

        connectButton.setOnClickListener {
            ChatServerConnector.ipAddress = ipText.text.toString()
            Thread(ChatServerConnector).start()
            val intent = Intent(this, LoginActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}
