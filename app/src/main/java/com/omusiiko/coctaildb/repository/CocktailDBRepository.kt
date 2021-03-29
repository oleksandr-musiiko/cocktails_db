package com.omusiiko.coctaildb.repository

import com.omusiiko.coctaildb.retrofit.entity.drink.Drink
import io.reactivex.Single

interface CocktailDBRepository {

    fun getDrinksByCategory(category: String): Single<List<Drink>>

    fun getCategories(): Single<List<String>>

}