package com.un.sherr.ui.main.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentSelectLocationBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.models.Country
import com.un.sherr.ui.main.vm.MainViewModel
import com.un.sherr.ui.main.adapters.CountriesAdapter
import com.un.sherr.utils.MapManagement
import com.un.sherr.utils.Utils
import javax.inject.Inject

class SelectLocationFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var mapManagement: MapManagement

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentSelectLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSelectLocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.vgMap.setOnClickListener(this)
        if (!viewModel.locationFilter.isNullOrBlank()) {
            binding.tvLocation.text = viewModel.locationFilter
        }

        val adapter = CountriesAdapter()
        binding.rvCountries.adapter = adapter
        adapter.addItems(mutableListOf(null, Country(""), Country(""), Country(""), null, Country(""), Country(""), Country(""), Country(""), Country("")))
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.vgMap -> {
                if (mapManagement.isServicesOK(requireActivity())) {
                    if (Utils.checkMapPermission(requireContext())) {
                        findNavController().navigate(R.id.action_selectLocationFragment_to_selectPositionMapFragment)
                    }
                }
            }
        }
    }

}
