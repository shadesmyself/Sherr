package com.un.sherr.models

import com.google.gson.annotations.SerializedName

data class ChatDirectMain (
    @SerializedName("user_id")
    val user_id : Int,
    @SerializedName("message")
    val message : String,
    @SerializedName("date_time")
    val date_time : String
): BaseModelResponse()