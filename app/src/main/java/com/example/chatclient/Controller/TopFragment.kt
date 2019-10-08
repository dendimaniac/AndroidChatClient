package com.example.chatclient.Controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatclient.Model.ActivityHelpers.TopRecyclerViewAdapter
import com.example.chatclient.Model.ChatConnector.ChatServerConnector
import com.example.chatclient.Model.Data.ChatMessage
import com.example.chatclient.Model.Data.Commands
import com.example.chatclient.Model.Data.ConnectorData
import com.example.chatclient.Model.Interfaces.IObserver
import com.example.chatclient.Model.MessagesHandlers.ChatMessager
import com.example.chatclient.R
import kotlinx.android.synthetic.main.fragment_chat.view.*
import kotlinx.android.synthetic.main.fragment_top.view.*

class TopFragment : Fragment(), IObserver {
    private val topRecyclerViewAdapter = TopRecyclerViewAdapter()
    private val linearLayoutManager = LinearLayoutManager(activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ChatServerConnector.registerObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_top, container, false)

        rootView.topRecycler.layoutManager = linearLayoutManager
        rootView.topRecycler.adapter = topRecyclerViewAdapter

        return rootView
    }

    override fun newMessage(chatMessage: ChatMessage) {
        activity!!.runOnUiThread {
            topRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}