package com.un.sherr.ui.profile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.base.BaseAdapter
import com.un.sherr.R
import com.un.sherr.models.Comment

class CommentsAdapter : BaseAdapter<Comment, RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            1 -> {
                CommentsHeaderVH(LayoutInflater.from(parent.context).inflate(R.layout.comment_header_item, parent, false))
            }
            else -> {
                CommentsVH(LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false))
            }
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CommentsVH)
            holder.bind(list[position])
        if (holder is CommentsHeaderVH)
            holder.bind()
    }

    class CommentsVH constructor(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comm: Comment?) {

        }
    }

    class CommentsHeaderVH constructor(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {

        }
    }
}