package com.omusiiko.coctaildb.repository

import com.omusiiko.coctaildb.retrofit.ApiService
import com.omusiiko.coctaildb.retrofit.entity.category.ModelCategories
import com.omusiiko.coctaildb.retrofit.entity.drink.Drink
import com.omusiiko.coctaildb.retrofit.entity.drink.ModelDrinks
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CocktailDBRepositoryImpl constructor() : CocktailDBRepository {

    override fun getDrinksByCategory(category: String): Single<List<Drink>> =
        Single.create { singleEmitter ->
            ApiService.getCoctailDBApi()
                .getDrinksByCategory(category)
                .enqueue(object : Callback<ModelDrinks> {
                    override fun onFailure(call: Call<ModelDrinks>, t: Throwable) {
                        singleEmitter.onError(t)
                    }

                    override fun onResponse(
                        call: Call<ModelDrinks>,
                        response: Response<ModelDrinks>
                    ) {
                        response.body()?.let { modelDrinks ->
                            singleEmitter.onSuccess(modelDrinks.drinks)
                        }
                    }
                })
        }

    override fun getCategories(): Single<List<String>> =
        Single.create { singleEmitter ->
            ApiService.getCoctailDBApi()
                .getListOfCategories()
                .enqueue(object : Callback<ModelCategories> {
                    override fun onFailure(call: Call<ModelCategories>, t: Throwable) {
                        singleEmitter.onError(t)
                    }

                    override fun onResponse(
                        call: Call<ModelCategories>,
                        response: Response<ModelCategories>
                    ) {
                        response.body()?.let { modelCategories ->
                            val resultListOfCategories = mutableListOf<String>()
                            modelCategories.categories.forEach {
                                resultListOfCategories.add(it.category)
                            }
                            singleEmitter.onSuccess(resultListOfCategories)
                        }
                    }
                })
        }

}