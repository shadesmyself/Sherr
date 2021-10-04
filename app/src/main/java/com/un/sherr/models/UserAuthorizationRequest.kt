package com.un.sherr.models

data class UserAuthorizationRequest(
    val grant_type: String = "password",
    val client_id: Long = 8,
    val client_secret: String = "vc3ZzOrNzRPf55Ebkgd6cyYnRHF8Jj0YgBN6v1lG",
    val username: String? = null,
    val password: String? = null,
    val scope: String = "*"
)
