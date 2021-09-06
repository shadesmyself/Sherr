package com.un.sherr.models


import com.google.gson.annotations.SerializedName

abstract class BaseModelResponse {
    @SerializedName("created_at")
    val createdAt: String? = null

    @SerializedName("id")
    open var id: Int? = null

    @SerializedName("updated_at")
    val updatedAt: String? = null
}