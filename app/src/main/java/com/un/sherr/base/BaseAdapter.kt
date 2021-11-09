package com.un.sherr.base

import androidx.recyclerview.widget.RecyclerView
import com.un.sherr.models.BaseModelResponse

abstract class BaseAdapter<T : BaseModelResponse?, U : RecyclerView.ViewHolder> : RecyclerView.Adapter<U>() {

    var onItemClick: ((Int) -> Unit)? = null

    var list = mutableListOf<T>()

    fun addItems(l: MutableList<T>) {
        if (l.isNullOrEmpty()) {
            list = arrayListOf()
            notifyDataSetChanged()
        } else if (list.isNullOrEmpty() || list.filterNotNull().map { it.id }.contains(l[0]!!.id)) {
            list = l
            notifyDataSetChanged()
        } else {
            list.addAll(l)
            notifyItemRangeInserted(list.size - 1, list.size + l.size)
        }
    }
}