package com.un.sherr.ui.favorites.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R

class AnnouncementAdapter: RecyclerView.Adapter<AnnouncementAdapter.AnnouncementVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementVH
    = AnnouncementVH(LayoutInflater.from(parent.context).inflate(R.layout.announcement_item, parent, false))

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: AnnouncementVH, position: Int) {
    }

    class AnnouncementVH(val view: View): RecyclerView.ViewHolder(view)
}