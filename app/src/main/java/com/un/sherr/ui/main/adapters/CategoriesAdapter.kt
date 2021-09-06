package com.un.sherr.ui.main.adapters

import android.graphics.drawable.PictureDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.un.sherr.R
import com.un.sherr.base.BaseAdapter
import com.un.sherr.custom.glide.SvgSoftwareLayerSetter
import com.un.sherr.models.CategoryResponse
import com.un.sherr.utils.Constants
import kotlinx.android.synthetic.main.category_main_item.view.*

class CategoriesAdapter : BaseAdapter<CategoryResponse, CategoriesAdapter.CategoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH =
        CategoryVH(LayoutInflater.from(parent.context).inflate(R.layout.category_main_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    class CategoryVH(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: CategoryResponse) {
            view.category_title.text = category.name
            val url = Constants.BASE_URL_IMG + category.icon
            val glide: RequestBuilder<PictureDrawable> = Glide.with(view.context).`as`(PictureDrawable::class.java)
            glide
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .listener(SvgSoftwareLayerSetter())
                .load(url)
                .into(view.logo_category)
        }
    }
}