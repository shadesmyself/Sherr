package com.un.sherr.ui.offer.adapters

import androidx.cardview.widget.CardView


interface CardAdapter {
    val baseElevation: Float
    val size: Int

    fun getCardViewAt(position: Int): CardView?

    companion object {
        const val MAX_ELEVATION_FACTOR = 4
    }
}