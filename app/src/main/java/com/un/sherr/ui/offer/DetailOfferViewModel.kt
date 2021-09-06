package com.un.sherr.ui.offer

import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModel
import com.un.sherr.models.GoodResponse
import com.un.sherr.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailOfferViewModel @Inject constructor(var api: Api) : BaseViewModel() {

    val ordersData = MutableLiveData<GoodResponse>()
    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun loadOrder(id: Int) {
        var disposable = api.getOrder(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    ordersData.value = result.data
                },
                {
                    onError(it)
                }
            )
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