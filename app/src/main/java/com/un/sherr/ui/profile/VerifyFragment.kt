package com.un.sherr.ui.profile


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.opensooq.supernova.gligar.GligarPicker
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.data.repository.VerificationRepository
import com.un.sherr.data.repository.VerificationRepositoryImpl
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.adapters.GoodsAdapter
import com.un.sherr.ui.profile.vm.ProfileViewModel
import com.un.sherr.ui.profile.vm.VerifyFragmentViewModel
import kotlinx.android.synthetic.main.fragment_verify.*
import java.io.File
import javax.inject.Inject
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.ByteArrayOutputStream
import okhttp3.RequestBody
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.MultipartReader


class VerifyFragment : BaseFragment() {
    val PICKER_REQUEST_CODE1 = 111
    val PICKER_REQUEST_CODE2 = 222

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private var firstPhoto: Array<String>? = null
    private var secondPhoto: Array<String>? = null

    private lateinit var viewModel: ProfileViewModel

    private lateinit var adapter: GoodsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_verify, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelProviderFactory
        ).get(ProfileViewModel::class.java)

        img1.setOnClickListener {
            GligarPicker().limit(1).requestCode(PICKER_REQUEST_CODE1).withFragment(this).show()
        }
        img2.setOnClickListener {
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
                        .into(img1)
                }
            }
            PICKER_REQUEST_CODE2 -> {
                secondPhoto =
                    data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT) as Array<String> // return list of selected images paths.
                if (!secondPhoto.isNullOrEmpty()) {
                    requestManager
                        .load(secondPhoto!![0])
                        .centerCrop()
                        .into(img2)
                }
            }
        }
    }

    private fun sendVerificationData() {
        tvSubmit.setOnClickListener {
            if (firstPhoto != null && secondPhoto != null) {
                val firstPhotoUpload = File(firstPhoto!![0])
                val secondPhotoUpload = File(secondPhoto!![0])
                viewModel.verificationData(firstPhotoUpload,secondPhotoUpload )
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(requireContext(), "Добавьте фото для веррификации", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
