package com.un.sherr.ui.offer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentLeaseBinding
import com.un.sherr.ui.MainActivity

class LeaseOfferFragment : BaseFragment() {
    companion object {
        val ID: String = "ID"
    }

    private lateinit var binding: FragmentLeaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.datePicker.setSelectorColor(android.R.color.transparent)
        binding.datePicker.addOnDateChangedListener(SingleDateAndTimePicker.OnDateChangedListener { displayed, date ->

        })
        binding.doneBtn.setOnClickListener {
            // navController.navigate(R.id.action_leaseOfferFragment_to_createCommentFragment)
            findNavController().navigate(R.id.action_leaseOfferFragment_to_payFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
