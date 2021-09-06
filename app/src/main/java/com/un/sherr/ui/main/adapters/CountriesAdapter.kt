package com.un.sherr.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.base.BaseAdapter
import com.un.sherr.R
import com.un.sherr.models.Country
import kotlinx.android.synthetic.main.select_region_item_header.view.*

class CountriesAdapter : BaseAdapter<Country?, RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int
    = when(list[position]){
        null -> 0
        else -> 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        1 -> CountriesVH(LayoutInflater.from(parent.context).inflate(R.layout.select_region_item, parent, false))
        else -> CountriesHeaderVH(LayoutInflater.from(parent.context).inflate(R.layout.select_region_item_header, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CountriesVH)
            holder.bind(list[position]!!)
        if (holder is CountriesHeaderVH)
            holder.bind()
    }

    class CountriesVH constructor(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(c: Country) {

        }
    }

    class CountriesHeaderVH constructor(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            when(adapterPosition){
                0 -> view.tvTitle.text = view.context.getText(R.string.last_requests)
                else -> view.tvTitle.text = view.context.getText(R.string.select_region)
            }
        }
    }
}