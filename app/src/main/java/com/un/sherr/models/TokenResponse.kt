package com.un.sherr.models

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("error")
    val error: String? = null,

    @SerializedName("error_description")
    val errorDescription: String? = null,

    @SerializedName("expires_in")
    val expiresIn: Int? = null,

    @SerializedName("hint")
    val hint: String? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("refresh_token")
    val refreshToken: String? = null,

    @SerializedName("token_type")
    val tokenType: String? = null
) {
    val isTokenExist: Boolean
        get() {
            if (!accessToken.isNullOrEmpty()) return true
            return false
        }
}