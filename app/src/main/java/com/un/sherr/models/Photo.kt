package com.un.sherr.models

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("image")
    val image: String?
)