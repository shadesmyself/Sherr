package com.un.sherr.models

data class UserAuthorizationRequest(
    val grant_type: String = "password",
    val client_id: Long = 4,
    val client_secret: String = "AECqnquqyzHgdae6J7SdnJ94udivf8BSInqQ4N5S",
    val username: String? = null,
    val password: String? = null,
    val scope: String = "*"
)
