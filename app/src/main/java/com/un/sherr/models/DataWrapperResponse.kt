package com.un.sherr.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataWrapperResponse<T>(
    @SerializedName("data")
    val data: T? = null,

    @SerializedName("error_code")
    val errorCode: Int? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("status")
    val status: String? = null
) : Serializable