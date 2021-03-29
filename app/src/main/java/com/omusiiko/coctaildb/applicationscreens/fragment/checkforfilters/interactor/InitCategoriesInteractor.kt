package com.omusiiko.coctaildb.applicationscreens.fragment.checkforfilters.interactor

import com.omusiiko.coctaildb.usecase.GetFiltersFromLocalStorageUseCase
import com.omusiiko.coctaildb.usecase.GetListOfCategoriesFromServerUseCase
import com.omusiiko.coctaildb.usecase.SaveFiltersToLocalStorageUseCase
import javax.inject.Inject

class InitCategoriesInteractor @Inject constructor(
    private val getListOfCategoriesFromServerUseCase: GetListOfCategoriesFromServerUseCase,
    private val getFiltersFromLocalStorageUseCase: GetFiltersFromLocalStorageUseCase,
    private val saveFiltersToLocalStorageUseCase: SaveFiltersToLocalStorageUseCase
) {

    fun getListOfCategoriesFromServer() = getListOfCategoriesFromServerUseCase.invoke()

    fun getFiltersFromLocalStorage() = getFiltersFromLocalStorageUseCase.invoke()

    fun saveFiltersToLocalStorage(filters: LinkedHashMap<String, Boolean>) =
        saveFiltersToLocalStorageUseCase.invoke(
            SaveFiltersToLocalStorageUseCase.Request(filters)
        )

}