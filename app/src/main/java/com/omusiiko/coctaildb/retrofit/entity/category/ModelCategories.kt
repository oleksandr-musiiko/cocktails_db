package com.omusiiko.coctaildb.retrofit.entity.category

import com.google.gson.annotations.SerializedName

data class ModelCategories(
    @SerializedName("drinks") val categories: List<Category>
)