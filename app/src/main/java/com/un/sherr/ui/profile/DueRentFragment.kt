package com.un.sherr.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.profile.adapters.ArchiveAdapter
import kotlinx.android.synthetic.main.fragment_due_rent.*

class DueRentFragment : BaseFragment() {

    var adapter = ArchiveAdapter(ArchiveAdapter.DUE_RENT_TYPE)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_due_rent, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRent.adapter = adapter
    }
}
