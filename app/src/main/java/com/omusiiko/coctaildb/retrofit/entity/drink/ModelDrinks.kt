package com.omusiiko.coctaildb.retrofit.entity.drink

import com.google.gson.annotations.SerializedName

data class ModelDrinks(

    @SerializedName("drinks") val drinks: List<Drink>
)