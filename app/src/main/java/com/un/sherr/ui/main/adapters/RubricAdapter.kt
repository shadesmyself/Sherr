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
import kotlinx.android.synthetic.main.rubric_item.view.*


class RubricAdapter : BaseAdapter<CategoryResponse, RubricAdapter.RubricVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RubricVH =
        RubricVH(LayoutInflater.from(parent.context).inflate(R.layout.rubric_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RubricVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    class RubricVH(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(categoryResponse: CategoryResponse) {
            view.title.text = categoryResponse.name
            val url = Constants.BASE_URL_IMG + categoryResponse.icon
            val glide: RequestBuilder<PictureDrawable> = Glide.with(view.context).`as`(PictureDrawable::class.java)

            glide
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .listener(SvgSoftwareLayerSetter())
                .load(url)
                .into(view.logo)

        }
    }
}