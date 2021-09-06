package com.un.sherr.ui.addgood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.un.sherr.R
import kotlinx.android.synthetic.main.add_photo_item.view.*

class AddPhotosAdapter constructor(private val requestManager: RequestManager, private val listener: OnPhotoClickListener): RecyclerView.Adapter<AddPhotosAdapter.AddPhotosVH>() {

    var list = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPhotosVH
    = AddPhotosVH(LayoutInflater.from(parent.context).inflate(R.layout.add_photo_item, parent, false), requestManager)

    override fun getItemCount(): Int = list.size + 1

    override fun onBindViewHolder(holder: AddPhotosVH, position: Int) {
        if (position < list.size){
            holder.bind(list[position])
        }else{
            holder.view.setOnClickListener { listener.onPhotoClick() }
        }
    }

    class AddPhotosVH constructor(val view: View, val requestManager: RequestManager): RecyclerView.ViewHolder(view){
        fun bind(s: String) {
            requestManager.load(s).into(view.ivPhotos)
        }
    }

    interface OnPhotoClickListener{
        fun onPhotoClick()
    }
}