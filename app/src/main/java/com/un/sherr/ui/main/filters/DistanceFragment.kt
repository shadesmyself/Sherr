package com.un.sherr.ui.main.filters

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.un.sherr.base.BaseMapFragment
import com.un.sherr.databinding.FragmentDistanceBinding
import com.un.sherr.ui.main.adapters.DistanceAdapter
import kotlin.math.ln

class DistanceFragment : BaseMapFragment(), DistanceAdapter.OnDistanceClickListener {

    private var map: GoogleMap? = null
    private lateinit var binding: FragmentDistanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDistanceBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDistance.adapter = DistanceAdapter(arrayListOf(0, 5, 10, 15, 20), this)
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map
        zoomCamera(10)
        map!!.uiSettings.setAllGesturesEnabled(false)
    }

    private fun zoomCamera(km: Int) {
        val metters = (km * 1300).toDouble()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        if (map != null)
            LocationServices.getFusedLocationProviderClient(requireActivity()).lastLocation.addOnCompleteListener { task ->
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
