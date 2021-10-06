package com.un.sherr.ui.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentDueRentBinding
import com.un.sherr.ui.profile.adapters.ArchiveAdapter

class DueRentFragment : BaseFragment() {

    var adapter = ArchiveAdapter(ArchiveAdapter.DUE_RENT_TYPE)
    private lateinit var binding: FragmentDueRentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDueRentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRent.adapter = adapter
    }
}
