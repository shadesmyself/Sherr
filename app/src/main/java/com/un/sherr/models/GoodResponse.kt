package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class GoodResponse(
    @SerializedName("category_id")
    val categoryId: Int?,

    @SerializedName("cost_day")
    val costDay: Int?,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("insurance")
    val insurance: Int?,

    @SerializedName("latitude")
    val latitude: String?,

    @SerializedName("longitude")
    val longitude: String?,

    @SerializedName("orderPhotos")
    val orderPhotos: OrderPhotos?,

    @SerializedName("request_passport")
    val requestPassport: Int?,

    @SerializedName("safe_deal")
    val safeDeal: Int?,

    @SerializedName("sub_category_id")
    val subCategoryId: Int?,

    @SerializedName("sum_insurance")
    val sumInsurance: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("user_id")
    val userId: Int?
): BaseModelResponse()