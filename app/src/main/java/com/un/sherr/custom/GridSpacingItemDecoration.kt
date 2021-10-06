package com.un.sherr.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration : RecyclerView.ItemDecoration() {

    private val spanCount = 2

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position

        when (position % spanCount) {
            1 -> {
                outRect.left = 30
            }
            0 -> {
                outRect.right = 30
            }
        }
    }
}