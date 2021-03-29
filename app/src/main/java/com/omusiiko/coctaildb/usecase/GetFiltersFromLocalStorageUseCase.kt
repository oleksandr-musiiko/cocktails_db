package com.omusiiko.coctaildb.usecase

import com.omusiiko.coctaildb.repository.FiltersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFiltersFromLocalStorageUseCase @Inject constructor(private val repository: FiltersRepository) :
    Function0<Single<LinkedHashMap<String, Boolean>>> {

    override fun invoke(): Single<LinkedHashMap<String, Boolean>> =
        getFiltersFromLocalStorage()

    private fun getFiltersFromLocalStorage(): Single<LinkedHashMap<String, Boolean>> =
        repository.getFiltersFromLocalStorage()

}
