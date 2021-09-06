package com.un.sherr.ui.favorites.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R
import kotlinx.android.synthetic.main.announcement_vp_item.view.*

class FavoritesVPAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = when(viewType){
        0 -> FavoritesVH(LayoutInflater.from(parent.context).inflate(R.layout.announcement_vp_item, parent, false))
        else -> SearchVH(LayoutInflater.from(parent.context).inflate(R.layout.announcement_vp_item, parent, false))
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoritesVH)
            holder.bind()
        if (holder is SearchVH)
            holder.bind()
    }

    class FavoritesVH constructor(val view: View): RecyclerView.ViewHolder(view){
        fun bind(){
            view.rvAnnouncement.adapter = AnnouncementAdapter()
        }
    }

    class SearchVH constructor(val view: View): RecyclerView.ViewHolder(view){
        fun bind(){
            view.rvAnnouncement.adapter = BookmarkSearchAdapter()
        }
    }

}