package com.un.sherr.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentPayBinding
import com.un.sherr.ui.MainActivity

class PayFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentPayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentPayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvPay.setOnClickListener(this)
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