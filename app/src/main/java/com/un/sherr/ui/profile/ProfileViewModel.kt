package com.un.sherr.ui.profile

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModel
import com.un.sherr.models.Avatar
import com.un.sherr.models.UserInformationResponse
import com.un.sherr.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var api: Api) : BaseViewModel() {

    val currentUserData = MutableLiveData<UserInformationResponse>()
    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val avatar = MutableLiveData<Avatar>()

    fun loadUserInformation() {
        var disposable = api.getCurrentUserInformation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    currentUserData.value = result.data
                }, {
                    onError(it)
                }
            )
    }

    fun uploadUserAvatar(image: Bitmap){
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val body = MultipartBody.Part.createFormData(
            "photo[content]", "photo",
            byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size)
        )
//        var disposable = api.uploadUserAvatar()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    currentUserData.value = result.data
//                }, {
//                    onError(it)
//                }
//            )
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