package com.un.sherr.ui.profile


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opensooq.supernova.gligar.GligarPicker
import com.un.sherr.base.BaseFragment

import com.un.sherr.R
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.adapters.GoodsAdapter
import kotlinx.android.synthetic.main.fragment_verify.*

class VerifyFragment : BaseFragment() {
    val PICKER_REQUEST_CODE1 = 111
    val PICKER_REQUEST_CODE2 = 222

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_verify, container, false)

    private lateinit var adapter: GoodsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img1.setOnClickListener {
            GligarPicker().limit(1).requestCode(PICKER_REQUEST_CODE1).withFragment(this).show()
        }
        img2.setOnClickListener {
            GligarPicker().limit(1).requestCode(PICKER_REQUEST_CODE2).withFragment(this).show()
        }
        tvSubmit.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            PICKER_REQUEST_CODE1 -> {
                val imagesList = data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT)// return list of selected images paths.
                if (!imagesList.isNullOrEmpty()) {
                    requestManager
                        .load(imagesList[0])
                        .centerCrop()
                        .into(img1)


                }
            }
            PICKER_REQUEST_CODE2 -> {
                val imagesList = data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT)// return list of selected images paths.
                if (!imagesList.isNullOrEmpty()) {
                    requestManager
                        .load(imagesList[0])
                        .centerCrop()
                        .into(img2)


                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

}
