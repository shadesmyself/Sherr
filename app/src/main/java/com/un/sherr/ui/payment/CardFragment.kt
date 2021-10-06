package com.un.sherr.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentCardBinding
import com.un.sherr.ui.MainActivity

class CardFragment : BaseFragment() {
    private lateinit var binding: FragmentCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.et1.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                binding.et2.requestFocus()
        }
        binding.et2.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                binding.et3.requestFocus()
        }
        binding.et3.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                binding.et4.requestFocus()
        }
        binding.et4.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                binding.et5.requestFocus()
        }
        binding.et5.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 2) {
                binding.et5.setText(binding.et5.text.toString() + "/")
                binding.et5.setSelection(binding.et5.text.length)
            }
            if (text.length == 5)
                binding.et6.requestFocus()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

}
