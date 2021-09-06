package com.un.sherr.base

import android.content.Intent
import com.un.sherr.SessionManager
import com.un.sherr.utils.Constants
import com.un.sherr.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


open class BaseActivity : DaggerAppCompatActivity() {

    companion object {
        const val TAG = "BaseActivity"
    }

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constants.ACCESS_LOCATION_PERMISSIONS -> {
                if (Utils.checkMapPermission(baseContext!!)) {
                    //getChatrooms()
                } else {
                    //mapManagement.getLocationPermission(this)
                }
            }
        }
    }

}