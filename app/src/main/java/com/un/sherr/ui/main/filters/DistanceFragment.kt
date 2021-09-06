package com.un.sherr.ui.main.filters

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.un.sherr.R
import com.un.sherr.base.BaseMapFragment
import com.un.sherr.ui.main.adapters.DistanceAdapter
import kotlinx.android.synthetic.main.fragment_distance.*
import kotlin.math.ln


class DistanceFragment : BaseMapFragment(), DistanceAdapter.OnDistanceClickListener {

    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_distance, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvDistance.adapter = DistanceAdapter(arrayListOf(0, 5, 10, 15, 20), this)
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        zoomCamera(10)
        map!!.uiSettings.setAllGesturesEnabled(false)
    }

    private fun zoomCamera(km: Int) {
        val metters = (km * 1300).toDouble()
        if (map != null)
            LocationServices.getFusedLocationProviderClient(activity!!).lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val location: Location? = task.result

                    val circle: Circle = map!!.addCircle(
                        CircleOptions().center(LatLng(location!!.latitude, location.longitude))
                            .radius(metters).strokeColor(Color.WHITE)
                    )
                    circle.isVisible = false

                    val cameraPosition = CameraPosition.Builder()
                        .target(LatLng(location.latitude, location.longitude))
                        .zoom(getZoomLevel(circle))
                        .build()

                    map!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                }
            }
    }

    private fun getZoomLevel(circle: Circle): Float {
        return (16 - ln(circle.radius / 500) / ln(2.0)).toFloat()
    }

    override fun onDistanceClick(value: Int) {
        zoomCamera(value)
    }


}
