package com.un.sherr.ui.authorization

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.base.BaseApplication
import com.un.sherr.base.BaseFragment
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_authorization.*
import javax.inject.Inject

class AuthorizationFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: AuthorizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_authorization, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(
            AuthorizationViewModel::class.java
        )
        setupListeners()
    }

    private fun setupListeners() {
        user_email_edit_text.addTextChangedListener {
            viewModel.setUsername(it.toString())
        }

        user_password_edit_text.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }

        authorization_clickable_text_view.setOnClickListener {
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