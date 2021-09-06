package com.un.sherr.ui.profile


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.opensooq.supernova.gligar.GligarPicker
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.adapters.GoodsAdapter
import kotlinx.android.synthetic.main.fragment_my_profile.*

class MyProfileFragment : BaseFragment() {
    val PICKER_REQUEST_CODE = 9

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_my_profile, container, false)

    private lateinit var adapter: GoodsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profile_img.setOnClickListener {
            GligarPicker().limit(1).requestCode(PICKER_REQUEST_CODE).withFragment(this).show()
        }
        verify_view.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_verifyFragment)
        }
        vgArchive.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_archivesFragment)
        }
        vgActive.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_activesFragment)
        }
        vgTermRent.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_dueRentFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            PICKER_REQUEST_CODE -> {
                val imagesList = data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT)// return list of selected images paths.
                if (!imagesList.isNullOrEmpty()) {
                    requestManager
                        .load(imagesList[0])
                        .into(profile_img)


                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(true)
    }

}
