package com.un.sherr.ui.profile.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.opensooq.supernova.gligar.GligarPicker
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentVerifyBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.adapters.GoodsAdapter
import com.un.sherr.ui.profile.vm.ProfileViewModel
import java.io.File
import javax.inject.Inject

class VerifyFragment : BaseFragment() {
    val PICKER_REQUEST_CODE1 = 111
    val PICKER_REQUEST_CODE2 = 222

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var binding: FragmentVerifyBinding
    private var firstPhoto: Array<String>? = null
    private var secondPhoto: Array<String>? = null

    private lateinit var viewModel: ProfileViewModel

    private lateinit var adapter: GoodsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentVerifyBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelProviderFactory
        ).get(ProfileViewModel::class.java)

        binding.img1.setOnClickListener {
            GligarPicker().limit(1).requestCode(PICKER_REQUEST_CODE1).withFragment(this).show()
        }
        binding.img2.setOnClickListener {
            GligarPicker().limit(1).requestCode(PICKER_REQUEST_CODE2).withFragment(this).show()
        }
        sendVerificationData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            PICKER_REQUEST_CODE1 -> {
                firstPhoto =
                    data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT) as Array<String>  // return list of selected images paths.
                if (!firstPhoto.isNullOrEmpty()) {
                    requestManager
                        .load(firstPhoto!![0])
                        .centerCrop()
                        .into(binding.img1)
                }
            }
            PICKER_REQUEST_CODE2 -> {
                secondPhoto =
                    data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT) as Array<String> // return list of selected images paths.
                if (!secondPhoto.isNullOrEmpty()) {
                    requestManager
                        .load(secondPhoto!![0])
                        .centerCrop()
                        .into(binding.img2)
                }
            }
        }
    }

    private fun sendVerificationData() {
        binding.tvSubmit.setOnClickListener {
            if (firstPhoto != null && secondPhoto != null) {
                val firstPhotoUpload = File(firstPhoto!![0])
                val secondPhotoUpload = File(secondPhoto!![0])
                viewModel.verificationData(firstPhotoUpload,secondPhotoUpload )
                requireActivity().onBackPressed()
            } else {
                showToast("Добавьте фото для веррификации")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
