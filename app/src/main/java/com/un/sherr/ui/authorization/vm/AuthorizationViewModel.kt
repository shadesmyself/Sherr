package com.un.sherr.ui.authorization.vm

import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModel
import com.un.sherr.models.TokenResponse
import com.un.sherr.models.UserAuthorizationRequest
import com.un.sherr.network.Api
import com.un.sherr.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(val api: Api) : BaseViewModel() {

    val userAuthorizationRequestLD = MutableLiveData<UserAuthorizationRequest>()
    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val tokenLD = MutableLiveData<TokenResponse>()
    private var username: String? = null
    private var password: String? = null

    init {
        if (userAuthorizationRequestLD.value == null) userAuthorizationRequestLD.value =
            UserAuthorizationRequest()
    }

    fun auth() {
        if (!password.isNullOrEmpty()  && !username.isNullOrEmpty()){
        val disposable = api.getUserAuthorizationToken(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.GRANT_TYPE,
            password!!,
            Constants.SCOPE,
            username!!
        )
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
    }
    fun setUsername(username: String) {
        this.username = username
    }

    fun setPassword(password: String) {
        this.password = password
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