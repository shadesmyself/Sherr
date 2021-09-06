package com.un.sherr.ui.profile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R
import com.un.sherr.base.BaseAdapter
import com.un.sherr.models.Good
import kotlinx.android.synthetic.main.archive_item.view.*

class ArchiveAdapter(var type: Int) : BaseAdapter<Good, RecyclerView.ViewHolder>() {
    companion object {
        val ARCHIVE_TYPE = 0
        val ACTIVE_TYPE = 1
        val DUE_RENT_TYPE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArchiveVH(LayoutInflater.from(parent.context).inflate(R.layout.archive_item, parent, false))


    override fun getItemCount(): Int = 10
//    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ArchiveVH)
            holder.bind()
    }

    class ArchiveVH constructor(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {

            when (itemViewType) {
                ARCHIVE_TYPE -> {
                    view.tvPay.text = view.resources.getString(R.string.activate)
                }
                ACTIVE_TYPE -> {
                    view.tvPay.text = view.resources.getString(R.string.advertise)
                }
                DUE_RENT_TYPE -> {
                    view.tvPay.text = view.resources.getString(R.string.to_chat)
                }
            }
        }
    }

}