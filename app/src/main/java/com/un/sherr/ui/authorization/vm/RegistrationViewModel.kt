package com.un.sherr.ui.authorization.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModel
import com.un.sherr.models.TokenResponse
import com.un.sherr.models.UserAuthorizationRequest
import com.un.sherr.models.UserRegistrationRequest
import com.un.sherr.models.UserRegistrationResponse
import com.un.sherr.network.Api
import com.un.sherr.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(var api: Api) : BaseViewModel() {

    val userRegistrationRequestLD = MutableLiveData<UserRegistrationRequest>()
    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val userRegistrationResponseLD = MutableLiveData<UserRegistrationResponse>()
    val userAuthorizationResponseLD = MutableLiveData<TokenResponse>()
    val userAuthorizationRequestLD = MutableLiveData<UserAuthorizationRequest>()
    private var email: String? = null
    private var password: String? = null

    init {
        if (userRegistrationRequestLD.value == null) userRegistrationRequestLD.value =
            UserRegistrationRequest()
        if (userAuthorizationRequestLD.value == null) userAuthorizationRequestLD.value =
            UserAuthorizationRequest()
    }

    fun userRegistration() {
        val disposable = api.userRegistration(userRegistrationRequestLD.value ?: return)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    userRegistrationResponseLD.value = result.data
                    userAuthorizationRequestLD.value = userAuthorizationRequestLD.value?.copy(
                        username = userRegistrationRequestLD.value?.name,
                        password = userRegistrationRequestLD.value?.password
                    )
                    auth()

                }, {
                    onError(it)
                }
            )
    }

    private fun auth() {
        val disposable = api.getUserAuthorizationToken(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE,
            password!!,
            Constants.SCOPE,
            email!!
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    userAuthorizationResponseLD.value = result
                }, {
                    onError(it)
                }
            )
    }

    fun setupName(name: String) {
        userRegistrationRequestLD.value = userRegistrationRequestLD.value?.copy(name = name)
    }

    fun setupEmail(email: String) {
        userRegistrationRequestLD.value = userRegistrationRequestLD.value?.copy(email = email)
        this.email = email
    }

    fun setupPassword(password: String) {
        userRegistrationRequestLD.value = userRegistrationRequestLD.value?.copy(password = password)
        this.password = password
    }

    override fun onStart() {
        progressDialog.value = true
    }

    override fun onFinish() {
        progressDialog.value = false
    }

    override fun onError(throwable: Throwable) {
        errorMessage.value = throwable.message
    }
}