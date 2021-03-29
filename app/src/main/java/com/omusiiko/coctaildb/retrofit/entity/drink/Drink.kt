package com.omusiiko.coctaildb.retrofit.entity.drink

import com.google.gson.annotations.SerializedName

data class Drink (

	@SerializedName("strDrink") val name : String = "",
	@SerializedName("strDrinkThumb") val photo : String = "",
	@SerializedName("idDrink") val id : Int
)