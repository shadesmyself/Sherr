package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("category_id")
    val categoryId: String?,

    @SerializedName("cost_day")
    val costDay: String?,

    @SerializedName("insurance")
    val insurance: String?,

    @SerializedName("latitude")
    val latitude: String?,

    @SerializedName("longitude")
    val longitude: String?,

    @SerializedName("request_passport")
    val requestPassport: String?,

    @SerializedName("safe_deal")
    val safeDeal: String?,

    @SerializedName("sub_category_id")
    val subCategoryId: String?,

    @SerializedName("sum_insurance")
    val sumInsurance: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("user_id")
    val userId: Int?
): BaseModelResponse()