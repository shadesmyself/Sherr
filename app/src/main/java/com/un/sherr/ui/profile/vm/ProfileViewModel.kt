package com.un.sherr.ui.profile.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModelCoroutines
import com.un.sherr.models.Avatar
import com.un.sherr.models.UserInformationResponse
import com.un.sherr.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var api: Api) : BaseViewModelCoroutines() {

    val currentUserData = MutableLiveData<UserInformationResponse>()
    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val avatar = MutableLiveData<Avatar>()

    private var passport: MultipartBody.Part? = null
    private var registration: MultipartBody.Part? = null
    private var avatarImage: MultipartBody.Part? = null

    fun loadUserInformation() {
        var disposable = api.getCurrentUserInformation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    currentUserData.value = result.data
                }, {
                    error(it)
                }
            )
    }

    fun uploadUserAvatar(image: File) {
//        val stream = ByteArrayOutputStream()
//        image.compress(Bitmap.CompressFormat.JPEG, 80, stream)
//        val byteArray = stream.toByteArray()
//        val body = MultipartBody.Part.createFormData(
//            "photo[content]", "photo",
//            byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size)
//        )
        val reqFilePassport = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), image)
        this.avatarImage = MultipartBody.Part.createFormData("photo", image.name, reqFilePassport)
        sendAvatar()
//        var disposable = api.uploadUserAvatar(avatarImage!!)
//           .subscribeOn(Schedulers.io())
//           .observeOn(AndroidSchedulers.mainThread())
//           .subscribe(
//               { result ->
//                   currentUserData.value = Avatar?
//               }, {
//                   onError(it)
//               }
//           )
    }

    fun verificationData(passport: File, registration: File) {

        val reqFilePassport =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), passport)
        val reqFileRegistration =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), registration)

        this.passport =
            MultipartBody.Part.createFormData("passport", passport.name, reqFilePassport)
        this.registration =
            MultipartBody.Part.createFormData(
                "registration", registration.name, reqFileRegistration)

        sendVerifyData()
    }

    private fun sendVerifyData() {
        runOperation {
            work {
                api.getVerify(passport!!, registration!!)
            }
        }
    }

    private fun sendAvatar() {
        runOperation {
            work {
                api.uploadUserAvatar(avatarImage!!)
            }
        }
    }
}
