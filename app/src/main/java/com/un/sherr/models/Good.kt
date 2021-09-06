package com.un.sherr.models

data class Good(
    val user_id: Int,
    val title: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val type: String,
    val category_id: String,
    val sub_category_id: String,
    val cost_day: String,
    val safe_deal: String,
    val insurance: String,
    val sum_insurance: String,
    val request_passport: String
): BaseModelResponse()