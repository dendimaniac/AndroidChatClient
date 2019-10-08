package com.example.chatclient.Model.ActivityHelpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatclient.Model.Data.ConnectorData
import com.example.chatclient.R
import kotlinx.android.synthetic.main.chat_recycler_item.view.*

class ChatRecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): RecyclerViewHolder {
        val itemView =
            LayoutInflater.from(vg.context).inflate(
                R.layout.chat_recycler_item, vg,
                false
            )
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ConnectorData.messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val view = holder.itemView
        val chatMessage = ConnectorData.messageList[position]

        view.messageTextView.text = chatMessage.message
        view.usernameTextView.text = chatMessage.username
        view.timestampTextView.text = chatMessage.timestamp
    }
}
