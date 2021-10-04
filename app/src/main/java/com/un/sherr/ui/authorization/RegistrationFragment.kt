package com.un.sherr.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.un.sherr.R
import com.un.sherr.base.BaseApplication
import com.un.sherr.base.BaseFragment
import com.un.sherr.di.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_authorization.user_email_edit_text
import kotlinx.android.synthetic.main.fragment_authorization.user_password_edit_text
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_registration, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(RegistrationViewModel::class.java)

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
        user_name_edit_text.addTextChangedListener {
            viewModel.setupName(it.toString())
        }

        user_email_edit_text.addTextChangedListener {
            viewModel.setupEmail(it.toString())
        }

        user_password_edit_text.addTextChangedListener {
            viewModel.setupPassword(it.toString())
        }

        registration_clickable_text_view.setOnClickListener {
            if (!isPasswordValid()) {
                return@setOnClickListener
            }
            viewModel.userRegistration()
        }
    }

    private fun isPasswordValid(): Boolean {
        if (user_password_edit_text.text.length < 6) {
            showToast("Пароль должен содержать не менее 6-ти символов")
            return false
        }
        return true
    }
}