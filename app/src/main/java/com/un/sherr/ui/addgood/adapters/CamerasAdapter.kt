package com.un.sherr.ui.addgood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.un.sherr.R
import kotlinx.android.synthetic.main.camera_photo_item.view.*

class CamerasAdapter constructor(val requestManager: RequestManager): RecyclerView.Adapter<CamerasAdapter.CamerasVH>() {

    var list = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamerasVH
    = CamerasVH(LayoutInflater.from(parent.context).inflate(R.layout.camera_photo_item, parent, false), requestManager)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CamerasVH, position: Int) {
        holder.bind(list[position])
    }

    class CamerasVH constructor(val view: View, val requestManager: RequestManager): RecyclerView.ViewHolder(view){
        fun bind(s: String) {
            requestManager.load(s).into(view.ivGallery)
        }
    }
}