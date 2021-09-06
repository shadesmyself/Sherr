package com.un.sherr.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.profile.adapters.ArchiveAdapter
import com.un.sherr.ui.profile.adapters.ArchiveAdapter.Companion.ACTIVE_TYPE
import kotlinx.android.synthetic.main.fragment_actives.*

class ActivesFragment : BaseFragment() {

    var adapter = ArchiveAdapter(ACTIVE_TYPE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_actives, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvActives.adapter = adapter
    }
}
