package com.un.sherr.ui.chat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.base.BaseAdapter
import com.un.sherr.R
import com.un.sherr.models.Chat

class ChatsAdapter : BaseAdapter<Chat, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ChatVH(LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChatVH) holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    class ChatVH constructor(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(c: Chat?) {
        }
    }
}