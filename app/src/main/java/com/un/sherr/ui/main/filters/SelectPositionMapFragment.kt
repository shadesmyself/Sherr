package com.un.sherr.ui.main.filters

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.un.sherr.R
import com.un.sherr.base.BaseMapFragment
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.main.MainViewModel
import com.un.sherr.utils.Utils
import kotlinx.android.synthetic.main.fragment_select_position_map.*
import java.io.IOException
import java.util.*
import javax.inject.Inject


class SelectPositionMapFragment : BaseMapFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_select_position_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onMapReady(map: GoogleMap) {
        if (Utils.checkMapPermission(context!!)) {
            tvSubmit.setOnClickListener {
                val a = map.cameraPosition.target
                val geocoder = Geocoder(context, Locale.getDefault())
                try {
                    val listAddresses = geocoder.getFromLocation(a.latitude, a.longitude, 1)
                    if (null != listAddresses && listAddresses.size > 0) {
                        viewModel.locationFilter = listAddresses[0].getAddressLine(0)
                    }
                    activity!!.onBackPressed()
                } catch (e: IOException) {
                    showToast(e.toString())
                }
            }
        }
        map.isMyLocationEnabled = true
    }
}
