package com.un.sherr.ui.chat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R
import com.un.sherr.base.BaseAdapter
import com.un.sherr.models.ChatDirectMain

class ChatsDirectAdapter : BaseAdapter<ChatDirectMain, ChatsDirectAdapter.ChatDirectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatDirectViewHolder =
        ChatDirectViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_my_item, parent, false)
        )

    override fun onBindViewHolder(holder: ChatDirectViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ChatDirectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text = itemView.findViewById<TextView>(R.id.text)
        private val dateTime = itemView.findViewById<TextView>(R.id.date)
        fun bind(item: ChatDirectMain) {
            text.text = item.message
            dateTime.text = item.date_time
        }
    }
}