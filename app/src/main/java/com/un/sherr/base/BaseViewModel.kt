package com.un.sherr.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun onStart()

    abstract fun onFinish()

    abstract fun onError(throwable: Throwable)
}