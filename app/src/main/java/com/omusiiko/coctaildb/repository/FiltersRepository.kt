package com.omusiiko.coctaildb.repository

import io.reactivex.Completable
import io.reactivex.Single

interface FiltersRepository {

    fun saveFiltersToLocalStorage(filters: LinkedHashMap<String, Boolean>) : Completable

    fun getFiltersFromLocalStorage(): Single<LinkedHashMap<String, Boolean>>

}