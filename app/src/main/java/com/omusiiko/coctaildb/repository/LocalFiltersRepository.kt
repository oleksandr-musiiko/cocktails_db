package com.omusiiko.coctaildb.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.omusiiko.coctaildb.common.LOCAL_FILTERS_SHARED_PREFERENCES_KEY
import com.omusiiko.coctaildb.common.LOCAL_FILTERS_STORAGE_SHARED_PREFERENCES
import io.reactivex.Completable
import io.reactivex.Single
import kotlin.collections.LinkedHashMap

class LocalFiltersRepository constructor(context: Context) : FiltersRepository {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        LOCAL_FILTERS_STORAGE_SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )

    override fun saveFiltersToLocalStorage(filters: LinkedHashMap<String, Boolean>): Completable {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        val filtersForSaveJson = Gson().toJson(filters)
        editor.putString(LOCAL_FILTERS_SHARED_PREFERENCES_KEY, filtersForSaveJson)
        editor.apply()
        return Completable.complete()
    }

    override fun getFiltersFromLocalStorage(): Single<LinkedHashMap<String, Boolean>> {
        val strJsonObject = sharedPreferences.getString(LOCAL_FILTERS_SHARED_PREFERENCES_KEY, "") ?: ""
        return if (strJsonObject.isNotEmpty()) {
            Single.just(
                Gson().fromJson<LinkedHashMap<String, Boolean>>(
                    strJsonObject, LinkedHashMap::class.java
                )
            )
        } else {
            Single.just(LinkedHashMap())
        }
    }

}