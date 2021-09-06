package com.un.sherr.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.un.sherr.R
import com.un.sherr.models.ClusterMarker


class MyClusterManagerRenderer constructor(val context: Context, val map: GoogleMap, val clusterManager: ClusterManager<ClusterMarker>
) : DefaultClusterRenderer<ClusterMarker>(context, map, clusterManager) {

    private val iconGenerator: IconGenerator
    private val imageView: ImageView
    private val markerWidth: Int
    private val markerHeight: Int

    init {
        iconGenerator = IconGenerator(context.applicationContext)
        imageView = ImageView(context.applicationContext)
        markerWidth = context.resources.getDimension(R.dimen.custom_marker_image).toInt()
        markerHeight = context.resources.getDimension(R.dimen.custom_marker_image).toInt()
        imageView.layoutParams = ViewGroup.LayoutParams(markerWidth, markerHeight)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        //val padding = context.resources.getDimension(R.dimen.custom_marker_padding).toInt()
        //imageView.setPadding(padding, padding, padding, padding)
        iconGenerator.setContentView(imageView)
    }

    override fun onBeforeClusterItemRendered(item: ClusterMarker, markerOptions: MarkerOptions) {
        imageView.setImageResource(item.icon)
        val icon = iconGenerator.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(item.title)
    }

    private fun bitmapDescriptorFromVector(context: Context, @DrawableRes vectorDrawableResourceId: Int): BitmapDescriptor? {
        val background = ContextCompat.getDrawable(context, R.drawable.orange_btn_with_border)
        background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(40, 20, vectorDrawable.intrinsicWidth + 40, vectorDrawable.intrinsicHeight + 20)
        val bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        background.draw(canvas)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


    override fun shouldRenderAsCluster(cluster: Cluster<ClusterMarker>?): Boolean {
        return false
    }
}