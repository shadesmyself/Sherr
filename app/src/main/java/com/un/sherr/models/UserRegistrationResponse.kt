package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class UserRegistrationResponse(
    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null
)