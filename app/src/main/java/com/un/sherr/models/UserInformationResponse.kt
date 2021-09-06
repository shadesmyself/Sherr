package com.un.sherr.models


import com.google.gson.annotations.SerializedName

data class UserInformationResponse(
    @SerializedName("email")
    val email: String? = null,

    @SerializedName("email_verified_at")
    val emailVerifiedAt: String? = null,

    @SerializedName("name")
    val name: String? = null
): BaseModelResponse()