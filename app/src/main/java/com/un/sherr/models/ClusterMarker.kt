package com.un.sherr.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class ClusterMarker constructor(val myPosition: LatLng, val myTitle: String, val mySnippet: String, val icon: Int): ClusterItem {

    override fun getSnippet(): String = mySnippet

    override fun getTitle(): String = myTitle

    override fun getPosition(): LatLng = myPosition
}