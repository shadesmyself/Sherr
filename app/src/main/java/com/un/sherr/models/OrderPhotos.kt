package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class OrderPhotos(
    @SerializedName("data")
    val data: List<Photo>?
)