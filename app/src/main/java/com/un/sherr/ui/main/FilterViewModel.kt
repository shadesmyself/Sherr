package com.un.sherr.ui.main

import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModel
import com.un.sherr.models.*
import com.un.sherr.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilterViewModel @Inject constructor(var api: Api) : BaseViewModel() {

    val categoriesLD = MutableLiveData<List<CategoryResponse>>()
    val subcategoriesLD = MutableLiveData<List<SubcategoryResponse>>()
    var locationFilter: String? = null

    var progressDialog = MutableLiveData<Boolean>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun loadSubcategories(categoryId: Int) {
        var disposable = api.getSubCategories("categoryId:${categoryId}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    subcategoriesLD.value = result.data
                },
                {
                    onError(it)
                }
            )
    }

    fun loadCategories() {
        var disposable = api.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    categoriesLD.value = result.data
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
        progressDialog.value = false
    }
}