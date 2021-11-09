package com.un.sherr.models

import com.google.gson.annotations.SerializedName

data class ChatDirectBase(
    @SerializedName("status")
    val status: String,
    @SerializedName("error_code")
    val error_code: Int,
    @SerializedName("data")
    val data: List<ChatDirectMain>,
    @SerializedName("message")
    val message: String
)
