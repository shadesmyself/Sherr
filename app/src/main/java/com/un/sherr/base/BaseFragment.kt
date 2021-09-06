package com.un.sherr.base

import android.widget.Toast
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment: DaggerFragment() {

    @Inject
    lateinit var requestManager: RequestManager


    fun showToast(str: String){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show()
    }
}