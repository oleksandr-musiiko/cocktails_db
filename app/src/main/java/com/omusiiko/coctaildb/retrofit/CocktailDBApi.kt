package com.omusiiko.coctaildb.retrofit

import com.omusiiko.coctaildb.retrofit.entity.category.ModelCategories
import com.omusiiko.coctaildb.retrofit.entity.drink.ModelDrinks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailDBApi {

    @GET("json/v1/1/filter.php?")
    fun getDrinksByCategory(
        @Query("c") category: String
    ): Call<ModelDrinks>

    @GET("json/v1/1/list.php?")
    fun getListOfCategories(
        @Query("c") request: String = "list"
    ): Call<ModelCategories>
}