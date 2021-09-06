package com.un.sherr.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.profile.adapters.ArchiveAdapter
import com.un.sherr.ui.profile.adapters.ArchiveAdapter.Companion.ARCHIVE_TYPE
import kotlinx.android.synthetic.main.fragment_archives.*

class ArchivesFragment : BaseFragment() {

    var adapter = ArchiveAdapter(ARCHIVE_TYPE)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_archives, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvArchives.adapter = adapter
    }
}
