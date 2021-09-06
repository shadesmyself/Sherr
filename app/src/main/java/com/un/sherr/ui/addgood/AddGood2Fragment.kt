package com.un.sherr.ui.addgood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import kotlinx.android.synthetic.main.fragment_add_good2.*

class AddGood2Fragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add_good2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vgPolicies.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.vgPolicies -> {findNavController().navigate(R.id.action_addGood2Fragment_to_policiesFragment)}
        }
    }

}
