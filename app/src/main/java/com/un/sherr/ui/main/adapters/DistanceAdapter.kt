package com.un.sherr.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R
import kotlinx.android.synthetic.main.distance_item.view.*


class DistanceAdapter constructor(private val list: ArrayList<Int>, val listener: OnDistanceClickListener) :
    RecyclerView.Adapter<DistanceAdapter.DistanceVH>() {

    var selectedPosition = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistanceVH =
        DistanceVH(LayoutInflater.from(parent.context).inflate(R.layout.distance_item, parent, false), this, listener)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DistanceVH, position: Int) {
        holder.bind(list[position])
    }

    class DistanceVH constructor(val view: View, val adapter: DistanceAdapter, val listener: OnDistanceClickListener) :
        RecyclerView.ViewHolder(view) {
        fun bind(value: Int) {
            view.tvDistance.text = String.format(view.context.getString(R.string.distance_value), value)
            view.setOnClickListener {
                it.tvDistance.setBackgroundResource(R.drawable.orange_btn)
                adapter.notifyItemChanged(adapter.selectedPosition)
                adapter.selectedPosition = adapterPosition
                listener.onDistanceClick(value)
            }
            if (adapterPosition == adapter.selectedPosition) {
                view.tvDistance.setBackgroundResource(R.drawable.orange_btn)
            } else {
                view.tvDistance.setBackgroundResource(R.drawable.gray_btn)
            }
        }
    }

    interface OnDistanceClickListener {
        fun onDistanceClick(value: Int)
    }
}