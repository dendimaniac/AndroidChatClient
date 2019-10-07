package com.example.chatclient.Controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclient.Model.ActivityHelpers.ChatRecyclerViewAdapter
import com.example.chatclient.Model.ChatConnector.ChatServerConnector
import com.example.chatclient.Model.Data.ChatMessage
import com.example.chatclient.Model.Data.Commands
import com.example.chatclient.Model.Data.ConnectorData
import com.example.chatclient.Model.Helpers.Time
import com.example.chatclient.Model.Interfaces.IObserver
import com.example.chatclient.Model.MessagesHandlers.ChatMessager
import com.example.chatclient.R
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.view.*

class ChatFragment : Fragment(), IObserver {
    private val chatRecyclerViewAdapter = ChatRecyclerViewAdapter()
    private val linearLayoutManager = LinearLayoutManager(activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ChatServerConnector.registerObserver(this)
        ConnectorData.canChat = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_chat, container, false)

        rootView.messageRecycler.layoutManager = linearLayoutManager
        rootView.messageRecycler.adapter = chatRecyclerViewAdapter

        rootView.sendButton.setOnClickListener {
            val chatMessage =
                ChatMessage(
                    ConnectorData.username,
                    Commands.Chat,
                    rootView.messageInput.text.toString(),
                    Time.getTime()
                )
            Thread(ChatMessager(chatMessage)).start()
            rootView.messageInput.setText("")
        }

        rootView.swipeContainer.setOnRefreshListener {
            val getHistory =
                ChatMessage(ConnectorData.username, Commands.History, "", "")
            Thread(ChatMessager(getHistory)).start()
            rootView.swipeContainer.isRefreshing = false
        }

        return rootView
    }


    override fun newMessage(chatMessage: ChatMessage) {
        activity!!.runOnUiThread {
            chatRecyclerViewAdapter.notifyDataSetChanged()
            messageRecycler.smoothScrollToPosition(chatRecyclerViewAdapter.itemCount - 1)
        }
    }
}