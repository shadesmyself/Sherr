package com.un.sherr.base

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.un.sherr.R
import com.un.sherr.utils.Constants
import com.un.sherr.utils.MapManagement
import javax.inject.Inject

abstract class BaseMapFragment: BaseFragment(), OnMapReadyCallback {

    @Inject
    lateinit var mapManagement: MapManagement

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapManagement.isMapsEnabled(requireActivity())
        initGoogleMap(savedInstanceState)
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(Constants.MAPVIEW_BUNDLE_KEY)
        }
        view?.findViewById<MapView>(R.id.mMapView)?.onCreate(mapViewBundle)
        view?.findViewById<MapView>(R.id.mMapView)?.getMapAsync(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(Constants.MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(Constants.MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        view?.findViewById<MapView>(R.id.mMapView)?.onSaveInstanceState(mapViewBundle)
    }


    override fun onResume() {
        super.onResume()
        view?.findViewById<MapView>(R.id.mMapView)?.onResume()
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<MapView>(R.id.mMapView)?.onStart()
    }

    override fun onStop() {
        super.onStop()
        view?.findViewById<MapView>(R.id.mMapView)?.onStop()
    }

    override fun onPause() {
        view?.findViewById<MapView>(R.id.mMapView)?.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        view?.findViewById<MapView>(R.id.mMapView)?.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        view?.findViewById<MapView>(R.id.mMapView)?.onLowMemory()
    }
}