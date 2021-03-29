package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.interactor

import com.omusiiko.coctaildb.usecase.GetDrinksByCategoryUseCase
import com.omusiiko.coctaildb.usecase.GetFiltersFromLocalStorageUseCase
import javax.inject.Inject

class DrinksInteractor @Inject constructor(
    private val getFiltersFromLocalStorageUseCase: GetFiltersFromLocalStorageUseCase,
    private val getDrinksByCategoryUseCase: GetDrinksByCategoryUseCase
) {

    fun getCategories() = getFiltersFromLocalStorageUseCase.invoke()

    fun getDrinksByCategory(category: String) = getDrinksByCategoryUseCase.invoke(
        GetDrinksByCategoryUseCase.Request(category)
    )

}