package com.un.sherr.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModelCoroutines : ViewModel() {

   private val errorText = MutableLiveData<String>()

    fun runOperation(
        operationBuilderFunc: CoroutineWorkBuilder.() -> Unit
    ) = viewModelScope.launch {
        val builder = CoroutineWorkBuilder().apply { operationBuilderFunc() }
        try {
            builder.progressFunction?.invoke(this, true)
            builder.workFunction?.invoke(this)
        } catch (error: Throwable) {
            errorText.postValue(error.toString())
            builder.errorFunction?.invoke(this, error)
        } finally {
            builder.progressFunction?.invoke(this, false)
        }
    }
}


class CoroutineWorkBuilder {
    internal var progressFunction: (suspend CoroutineScope.(Boolean) -> Unit)? = null
    internal var workFunction: (suspend CoroutineScope.() -> Unit)? = null
    internal var errorFunction: (suspend CoroutineScope.(Throwable) -> Unit)? = null

    fun progress(progressFunc: suspend CoroutineScope.(Boolean) -> Unit) {
        this.progressFunction = progressFunc
    }

    fun work(workFunc: suspend CoroutineScope.() -> Unit) {
        this.workFunction = workFunc
    }

    fun error(errorFunc: suspend CoroutineScope.(Throwable) -> Unit) {
        this.errorFunction = errorFunc
    }
}