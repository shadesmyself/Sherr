package com.un.sherr.ui.addgood.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.databinding.FragmentAddGood2Binding

class AddGood2Fragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentAddGood2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddGood2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vgPolicies.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.vgPolicies -> {
                findNavController().navigate(R.id.action_addGood2Fragment_to_policiesFragment)
            }
        }
    }

}
