package com.un.sherr.ui.addgood.vm

import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModel
import com.un.sherr.models.OrderRequest
import com.un.sherr.models.OrderResponse
import com.un.sherr.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddGoodViewModel @Inject constructor(var api: Api) : BaseViewModel() {

    val ordersData = MutableLiveData<OrderResponse>()
    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val ordersRequestLD = MutableLiveData<OrderRequest>()

    init {
        if (ordersRequestLD.value == null) ordersRequestLD.value = OrderRequest()
    }

    fun createOrder() {
        var disposable = api.createOrder(ordersRequestLD.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    ordersData.value = result.data
                }, {
                    onError(it)
                }
            )
    }

    fun setTitle(title: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(title = title)
        }
    }

    fun setCategoryId(categoryId: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(categoryId = categoryId)
        }
    }

    fun setCostDay(costDay: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(costDay = costDay)
        }
    }

    fun setDescription(description: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(description = description)
        }
    }

    fun setInsurance(insurance: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(insurance = insurance)
        }
    }

    fun setLatitude(latitude: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(latitude = latitude)
        }
    }

    fun setLongitude(longitude: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(longitude = longitude)
        }
    }

    fun setPhotos(photos: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(photos = photos)
        }
    }

    fun setRequestPassport(requestPassport: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(requestPassport = requestPassport)
        }
    }

    fun setSafeDeal(safeDeal: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(safeDeal = safeDeal)
        }
    }

    fun setSubCategoryId(subCategoryId: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(subCategoryId = subCategoryId)
        }
    }

    fun setSumInsurance(sumInsurance: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(sumInsurance = sumInsurance)
        }
    }

    fun setType(type: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(type = type)
        }
    }

    fun setUserId(userId: String) {
        ordersRequestLD.value?.let {
            ordersRequestLD.value = it.copy(userId = userId)
        }
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