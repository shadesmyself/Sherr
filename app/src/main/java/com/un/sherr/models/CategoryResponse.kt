package com.un.sherr.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryResponse(
    @SerializedName("icon")
    val icon: String?,

    @SerializedName("name")
    val name: String?,

    val categoryType: CategoriesType? = null,

) : BaseModelResponse(), Serializable {

    override var id: Int?
        get() = super.id
        set(value) {}
}