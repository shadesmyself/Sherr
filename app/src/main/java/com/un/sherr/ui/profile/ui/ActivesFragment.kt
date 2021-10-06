package com.un.sherr.ui.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentActivesBinding
import com.un.sherr.ui.profile.adapters.ArchiveAdapter
import com.un.sherr.ui.profile.adapters.ArchiveAdapter.Companion.ACTIVE_TYPE

class ActivesFragment : BaseFragment() {

    var adapter = ArchiveAdapter(ACTIVE_TYPE)
    private lateinit var binding: FragmentActivesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActivesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvActives.adapter = adapter
    }
}
