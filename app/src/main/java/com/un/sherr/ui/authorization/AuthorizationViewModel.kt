package com.un.sherr.ui.authorization

import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModel
import com.un.sherr.models.TokenResponse
import com.un.sherr.models.UserAuthorizationRequest
import com.un.sherr.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(val api: Api) : BaseViewModel() {

    val userAuthorizationRequestLD = MutableLiveData<UserAuthorizationRequest>()
    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val tokenLD = MutableLiveData<TokenResponse>()

    init {
        if (userAuthorizationRequestLD.value == null) userAuthorizationRequestLD.value = UserAuthorizationRequest()
    }

    fun auth() {
        val disposable = api.getUserAuthorizationToken(userAuthorizationRequestLD.value ?: return)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    tokenLD.value = result
                }, {
                    onError(it)
                }
            )
    }

    fun setUsername(username :String) {
        userAuthorizationRequestLD.value = userAuthorizationRequestLD.value?.copy(username = username)
    }

    fun setPassword(password :String) {
        userAuthorizationRequestLD.value = userAuthorizationRequestLD.value?.copy(password = password)
    }


    override fun onStart() {
        progressDialog.value = true
    }

    override fun onFinish() {
        progressDialog.value = false
    }

    override fun onError(throwable: Throwable) {
        progressDialog.value = false
    }
}