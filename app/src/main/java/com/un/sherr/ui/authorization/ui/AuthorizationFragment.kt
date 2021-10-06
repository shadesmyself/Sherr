package com.un.sherr.ui.authorization.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.un.sherr.base.BaseApplication
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentAuthorizationBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.authorization.vm.AuthorizationViewModel
import javax.inject.Inject

class AuthorizationFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: AuthorizationViewModel

    private lateinit var binding: FragmentAuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAuthorizationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(
            AuthorizationViewModel::class.java
        )
        setupListeners()
    }

    private fun setupListeners() {
        binding.userEmailEditText.addTextChangedListener {
            viewModel.setUsername(it.toString())
        }

        binding.userPasswordEditText.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }

        binding.authorizationClickableTextView.setOnClickListener {
            viewModel.auth()
            viewModel.tokenLD.observe(requireActivity(), {
                if (it.isTokenExist) {
                    BaseApplication.userToken
                        .edit()
                        .putString("UserToken" , it.accessToken)
                        .apply()
                    requireActivity().onBackPressed()
                }
            })
        }
    }
}