package com.example.chatclient.Model.ActivityHelpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatclient.Model.Data.ConnectorData
import com.example.chatclient.R
import kotlinx.android.synthetic.main.top_recycler_item.view.*

class TopRecyclerViewAdapter : RecyclerView.Adapter<TopViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(vg.context).inflate(
                R.layout.top_recycler_item, vg,
                false
            )
        return TopViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ConnectorData.topChatterMap.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val view = holder.itemView

        view.topUsernameTextView.text = ConnectorData.topChatterMap.keys.elementAt(position)
        view.topCountTextView.text =
            ConnectorData.topChatterMap.values.elementAt(position).toString()
    }
}