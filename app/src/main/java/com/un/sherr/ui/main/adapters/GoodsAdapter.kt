package com.un.sherr.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R
import com.un.sherr.base.BaseAdapter
import com.un.sherr.models.GoodResponse
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
        }
    }
}