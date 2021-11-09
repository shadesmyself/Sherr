package com.un.sherr.models

data class ChatDirectResponse(
    val status: String,
    val error_code: Int,
    val data: List<ChatDirectMain>
)
