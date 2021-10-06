package com.un.sherr.ui.authorization.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.un.sherr.base.BaseApplication
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentRegistrationBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.authorization.vm.RegistrationViewModel
import javax.inject.Inject

class RegistrationFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: RegistrationViewModel
private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(
            RegistrationViewModel::class.java)

        viewModel.userAuthorizationResponseLD.observe(requireActivity(), {
            if(it.isTokenExist){
                BaseApplication.userToken
                    .edit()
                    .putString("UserToken" , it.accessToken)
                    .apply()
                requireActivity().onBackPressed()
            }
        })
        setupListeners()
    }

    private fun setupListeners() {
        binding.userNameEditText.addTextChangedListener {
            viewModel.setupName(it.toString())
        }

        binding.userEmailEditText.addTextChangedListener {
            viewModel.setupEmail(it.toString())
        }

        binding.userPasswordEditText.addTextChangedListener {
            viewModel.setupPassword(it.toString())
        }

        binding.registrationClickableTextView.setOnClickListener {
            if (!isPasswordValid()) {
                return@setOnClickListener
            }
            viewModel.userRegistration()
        }
    }

    private fun isPasswordValid(): Boolean {
        if (binding.userPasswordEditText.text.length < 6) {
            showToast("Пароль должен содержать не менее 6-ти символов")
            return false
        }
        return true
    }
}