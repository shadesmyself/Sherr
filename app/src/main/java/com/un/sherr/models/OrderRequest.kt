package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("category_id")
    val categoryId: String? = null,

    @SerializedName("cost_day")
    val costDay: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("insurance")
    val insurance: String? = null,

    @SerializedName("latitude")
    val latitude: String? = null,

    @SerializedName("longitude")
    val longitude: String? = null,

    @SerializedName("photos[]")
    val photos: String? = null,

    @SerializedName("request_passport")
    val requestPassport: String? = null,

    @SerializedName("safe_deal")
    val safeDeal: String? = null,

    @SerializedName("sub_category_id")
    val subCategoryId: String? = null,

    @SerializedName("sum_insurance")
    val sumInsurance: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("user_id")
    val userId: String? = null
)