package com.example.chatclient.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclient.Model.*
import com.example.chatclient.R
import kotlinx.android.synthetic.main.activity_chat.*
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ChatActivity : AppCompatActivity(), IObserver {
    private lateinit var chatRecyclerViewAdapter: ChatRecyclerViewAdapter;
    private val formatter =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        ChatServerConnector.registerObserver(this)

        messageRecycler.layoutManager = LinearLayoutManager(this)
        chatRecyclerViewAdapter = ChatRecyclerViewAdapter(this)
        messageRecycler.adapter = chatRecyclerViewAdapter

        sendButton.setOnClickListener {
            val currentTime = Instant.now()
            val timeStamp = formatter.format(currentTime)

            val chatMessage =
                ChatMessage(messageInput.text.toString(), ConnectorData.username, timeStamp)
            Thread(ChatMessager(chatMessage)).start()
        }
    }

    override fun onStop() {
        super.onStop()
        ChatServerConnector.socket.close()
    }

    override fun newMessage(chatMessage: ChatMessage) {
        runOnUiThread {
            chatRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}
