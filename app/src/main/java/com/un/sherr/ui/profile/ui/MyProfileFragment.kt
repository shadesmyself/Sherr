package com.un.sherr.ui.profile.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.opensooq.supernova.gligar.GligarPicker
import com.un.sherr.R
import com.un.sherr.base.BaseApplication
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentMyProfileBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.dialog.DialogWithTwoButtons
import com.un.sherr.ui.main.adapters.GoodsAdapter
import com.un.sherr.ui.profile.vm.ProfileViewModel
import java.io.File
import javax.inject.Inject

class MyProfileFragment : BaseFragment() {
    val PICKER_REQUEST_CODE = 9

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentMyProfileBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(layoutInflater)
        return binding.root
    }
    private lateinit var adapter: GoodsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(ProfileViewModel::class.java)

        binding.profileImg.setOnClickListener {
            GligarPicker().limit(1).requestCode(PICKER_REQUEST_CODE).withFragment(this).show()
        }
        binding.verifyView.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_verifyFragment)
        }
        binding.vgArchive.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_archivesFragment)
        }
        binding.vgActive.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_activesFragment)
        }
        binding.vgTermRent.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_dueRentFragment)
        }
        binding.vgExit.setOnClickListener {
            val userToken = BaseApplication.userToken.getString("UserToken", "")
            if (userToken == "")
                return@setOnClickListener
            showDialog(getString(R.string.exit_hint))
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
                        .into(binding.profileImg)
                    val avatar = File(imagesList[0])
                    viewModel.uploadUserAvatar(avatar)
                }
            }
        }
    }

    private fun showDialog(title: String) {
        val dialog = DialogWithTwoButtons.newInstance(
            title = title,
            firstButtonText = getString(R.string.exit),
            secondButtonText = getString(R.string.cancel))
        dialog.isCancelable = false
        dialog.apply {
            onFirstButtonClick = {
                BaseApplication.userToken
                    .edit()
                    .clear()
                    .apply()
                findNavController().navigate(R.id.mainPageFragment)
            }
            onSecondButtonClick = {
          dialog.dismiss()
            }
        }


        dialog.show(requireActivity().supportFragmentManager, DialogWithTwoButtons::class.java.simpleName)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(true)
    }
}
