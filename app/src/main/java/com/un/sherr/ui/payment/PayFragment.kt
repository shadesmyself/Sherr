package com.un.sherr.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_pay.*

class PayFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pay, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPay.setOnClickListener(this)
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tvPay -> {findNavController().navigate(R.id.action_payFragment_to_cardFragment)}
        }
    }
}