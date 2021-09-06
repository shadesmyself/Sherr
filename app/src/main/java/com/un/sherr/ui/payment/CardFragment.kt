package com.un.sherr.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_card.*

class CardFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_card, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et1.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                et2.requestFocus()
        }
        et2.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                et3.requestFocus()
        }
        et3.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                et4.requestFocus()
        }
        et4.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 4)
                et5.requestFocus()
        }
        et5.doOnTextChanged { text, _, _, _ ->
            if (text!!.length == 2) {
                et5.setText(et5.text.toString() + "/")
                et5.setSelection(et5.text.length)
            }
            if (text.length == 5)
                et6.requestFocus()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

}
