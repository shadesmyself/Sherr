package com.un.sherr.models

data class MainErrorModel(
    override var message: String?,
    var error_code: Int,
    var messages: List<String>?
) : Throwable()