package com.un.sherr.ui.profile.vm

import android.util.Log
import com.un.sherr.base.BaseViewModelCoroutines
import com.un.sherr.data.repository.VerificationRepository
import com.un.sherr.network.Api
import java.io.File
import javax.inject.Inject

class VerifyFragmentViewModel () : BaseViewModelCoroutines() {

    private var passport: File? = null
    private var registration: File? = null

    private fun sendVerificationData() {
        runOperation {
            work{
//                api.getCategories()
            }
        }
    }
    fun verificationData(passport: File, registration: File) {
        this.passport = passport
        this.registration = registration
        Log.e("Verification View Model", "verificationData: ${this.passport}")
        Log.e("Verification View Model", "verificationData: ${this.registration}")
        sendVerificationData()
    }
}