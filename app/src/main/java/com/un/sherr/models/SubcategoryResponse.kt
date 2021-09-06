package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class SubcategoryResponse(
    @SerializedName("category_id")
    val categoryId: Int?,

    @SerializedName("name")
    val name: String?
) : BaseModelResponse()