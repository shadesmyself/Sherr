package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class RubricResponse(
    @SerializedName("icon")
    val icon: String?,

    @SerializedName("name")
    val name: String?
) : BaseModelResponse()