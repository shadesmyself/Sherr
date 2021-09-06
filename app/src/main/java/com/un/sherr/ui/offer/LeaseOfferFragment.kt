package com.un.sherr.ui.offer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_lease.*


class LeaseOfferFragment : BaseFragment() {
    companion object {
        val ID: String = "ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_lease, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date_picker.setSelectorColor(android.R.color.transparent)
        date_picker.addOnDateChangedListener(SingleDateAndTimePicker.OnDateChangedListener { displayed, date ->

        })
        done_btn.setOnClickListener {
           // navController.navigate(R.id.action_leaseOfferFragment_to_createCommentFragment)
            findNavController().navigate(R.id.action_leaseOfferFragment_to_payFragment)
        }

    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
