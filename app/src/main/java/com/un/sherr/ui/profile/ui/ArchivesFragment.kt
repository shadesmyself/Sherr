package com.un.sherr.ui.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentArchivesBinding
import com.un.sherr.ui.profile.adapters.ArchiveAdapter
import com.un.sherr.ui.profile.adapters.ArchiveAdapter.Companion.ARCHIVE_TYPE

class ArchivesFragment : BaseFragment() {

    var adapter = ArchiveAdapter(ARCHIVE_TYPE)
    private lateinit var binding: FragmentArchivesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArchivesBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvArchives.adapter = adapter
    }
}
