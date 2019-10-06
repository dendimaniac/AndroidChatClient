package com.example.chatclient.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclient.Model.*
import com.example.chatclient.R
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), IObserver {
    private lateinit var chatRecyclerViewAdapter: ChatRecyclerViewAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        ChatServerConnector.registerObserver(this)
        ConnectorData.canChat = true

        messageRecycler.layoutManager = LinearLayoutManager(this)
        chatRecyclerViewAdapter = ChatRecyclerViewAdapter(this)
        messageRecycler.adapter = chatRecyclerViewAdapter

        sendButton.setOnClickListener {
            val chatMessage =
                ChatMessage(
                    ConnectorData.username,
                    Commands.Chat,
                    messageInput.text.toString(),
                    Time.getTime()
                )
            Thread(ChatMessager(chatMessage)).start()
            messageInput.setText("")
        }
    }


    override fun newMessage(chatMessage: ChatMessage) {
        runOnUiThread {
            chatRecyclerViewAdapter.notifyDataSetChanged()
            messageRecycler.scrollToPosition(chatRecyclerViewAdapter.itemCount - 1)
        }
    }

    override fun onStop() {
        super.onStop()
        ConnectorData.canChat = false
    }

    override fun onDestroy() {
        super.onDestroy()
        ChatServerConnector.quit()
    }
}
