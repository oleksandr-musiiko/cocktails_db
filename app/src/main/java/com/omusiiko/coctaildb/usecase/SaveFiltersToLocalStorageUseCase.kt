package com.omusiiko.coctaildb.usecase

import com.omusiiko.coctaildb.repository.FiltersRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveFiltersToLocalStorageUseCase @Inject constructor(private val repository: FiltersRepository) :
    Function1<SaveFiltersToLocalStorageUseCase.Request, Completable> {

    override fun invoke(request: Request): Completable =
        saveFiltersToLocalStorage(request.filters)

    private fun saveFiltersToLocalStorage(filters: LinkedHashMap<String, Boolean>): Completable =
        repository.saveFiltersToLocalStorage(filters)

    data class Request(val filters: LinkedHashMap<String, Boolean>)

}
