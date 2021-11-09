package com.un.sherr.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.un.sherr.R
import com.un.sherr.base.BaseAdapter
import com.un.sherr.models.GoodResponse
import com.un.sherr.utils.Constants.BASE_URL_IMG
import kotlinx.android.synthetic.main.good_item.view.*

class GoodsAdapter : BaseAdapter<GoodResponse, GoodsAdapter.GoodsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsVH =
        GoodsVH(LayoutInflater.from(parent.context).inflate(R.layout.good_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GoodsVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    class GoodsVH constructor(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(good: GoodResponse) {
            view.title.text = good.title
            view.price.text = view.context.resources.getString(R.string.price_value, good.costDay.toString())
            if (good.orderPhotos!!.data!!.isNotEmpty()){
                Picasso.with(itemView.context)
                    .load(BASE_URL_IMG + good.orderPhotos.data!![0].image)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(view.img_order)
            } else{
                Picasso.with(itemView.context)
                    .load(R.drawable.place_holder)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(view.img_order)
            }
        }
    }
}