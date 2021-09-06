package com.un.sherr.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.R
import com.un.sherr.base.BaseAdapter
import com.un.sherr.models.SubcategoryResponse
import kotlinx.android.synthetic.main.subcategory_item.view.*

class SubcategoriesAdapter : BaseAdapter<SubcategoryResponse, SubcategoriesAdapter.SubcategoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryVH =
        SubcategoryVH(LayoutInflater.from(parent.context).inflate(R.layout.category_main_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SubcategoryVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    class SubcategoryVH(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(subcategoryResponse: SubcategoryResponse) {
            view.title.text = subcategoryResponse.name
        }
    }
}