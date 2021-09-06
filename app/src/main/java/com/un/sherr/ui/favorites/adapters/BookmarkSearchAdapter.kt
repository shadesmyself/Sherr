package com.un.sherr.ui.favorites.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R

class BookmarkSearchAdapter: RecyclerView.Adapter<BookmarkSearchAdapter.BookmarkSearchVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkSearchVH
            = BookmarkSearchVH(LayoutInflater.from(parent.context).inflate(R.layout.bookmark_search_item, parent, false))

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: BookmarkSearchVH, position: Int) {
    }

    class BookmarkSearchVH(val view: View): RecyclerView.ViewHolder(view)
}