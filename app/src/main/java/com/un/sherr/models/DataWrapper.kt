package com.un.sherr.models

import java.io.Serializable

class DataWrapper<T> : Serializable {

    var data: T? = null
    var success: Boolean? = null
    var message: String? = null
    var error_code: String? = null

}

