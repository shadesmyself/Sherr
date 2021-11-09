package com.un.sherr.models

import com.google.gson.annotations.SerializedName

data class Chats(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("last_message")
    val last_message: String,
    @SerializedName("last_message_time")
    val last_message_time: String
)
