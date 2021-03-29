package com.omusiiko.coctaildb.applicationscreens.fragment.filters.interactor

import com.omusiiko.coctaildb.usecase.GetFiltersFromLocalStorageUseCase
import com.omusiiko.coctaildb.usecase.SaveFiltersToLocalStorageUseCase
import javax.inject.Inject

class FiltersInteractor @Inject constructor(
    private val getFiltersFromLocalStorageUseCase: GetFiltersFromLocalStorageUseCase,
    private val saveFiltersToLocalStorageUseCase: SaveFiltersToLocalStorageUseCase
) {

    fun getFiltersFromLocalStorage() = getFiltersFromLocalStorageUseCase.invoke()

    fun saveFiltersToLocalStorage(filters: LinkedHashMap<String, Boolean>) =
        saveFiltersToLocalStorageUseCase.invoke(
            SaveFiltersToLocalStorageUseCase.Request(filters)
        )

}