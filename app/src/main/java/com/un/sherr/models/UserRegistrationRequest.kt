package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class UserRegistrationRequest(
    @SerializedName("email")
    val email: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("password")
    val password: String? = null
)