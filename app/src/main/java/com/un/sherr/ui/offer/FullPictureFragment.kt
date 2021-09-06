package com.un.sherr.ui.offer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_picture.*

class FullPictureFragment : BaseFragment() {

    companion object {
        val IMG: String = "IMG"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_picture, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestManager
            .load(arguments!!.get(IMG))
            .into(img)

        back_btn.setOnClickListener { activity!!.onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
