package com.omusiiko.coctaildb.applicationscreens.fragment.checkforfilters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.BaseViewModel
import com.omusiiko.coctaildb.applicationscreens.fragment.checkforfilters.interactor.InitCategoriesInteractor
import com.omusiiko.coctaildb.repository.string.StringRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckForFiltersViewModel @Inject constructor(
    private val interactor: InitCategoriesInteractor,
    private val stringRepository: StringRepository
) : BaseViewModel() {

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private var _navigateToDrinksFragment = MutableLiveData<Boolean>()
    val navigateToDrinksFragment: LiveData<Boolean>
        get() = _navigateToDrinksFragment

    fun checkForUpdateFilters() {
        Single.zip(
            getFiltersFromLocalStorageSingle(),
            getCategoriesFromServerSingle(),
            BiFunction<LinkedHashMap<String, Boolean>, List<String>, DataForCompare> { filters, categories ->
                DataForCompare(
                    filters,
                    categories
                )
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ dataForCompare ->
                if (isFiltersAndCategoriesSame(dataForCompare)) {
                    _navigateToDrinksFragment.value = true
                } else {
                    val filters = generateFiltersForSaveToLocalStorage(
                        dataForCompare.filtersFromLocalStorage,
                        dataForCompare.categoriesFromServer
                    )
                    saveFilterToLocalStorage(filters)
                }
            }, { e ->
                _error.value = e
                Log.e("ERROR", e.message, e)
            })
            .run(compositeDisposable::add)
    }

    private fun generateFiltersForSaveToLocalStorage(
        filters: LinkedHashMap<String, Boolean>,
        categories: List<String>
    ): LinkedHashMap<String, Boolean> {
        val resultFilters = LinkedHashMap<String, Boolean>()
        categories.forEach { category ->
            val flagToFilter = filters[category] ?: true
            resultFilters[category] = flagToFilter
        }
        return resultFilters
    }

    private fun saveFilterToLocalStorage(filters: LinkedHashMap<String, Boolean>) {
        interactor.saveFiltersToLocalStorage(filters)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _message.value = stringRepository.getString(R.string.filters_was_update_from_server)
                _navigateToDrinksFragment.value = true
            }, { e ->
                _error.value = e
                Thread.sleep(3000)
                saveFilterToLocalStorage(filters)
            })
            .run(compositeDisposable::add)
    }

    private fun getFiltersFromLocalStorageSingle() = interactor.getFiltersFromLocalStorage()

    private fun getCategoriesFromServerSingle() = interactor.getListOfCategoriesFromServer()

    private fun isFiltersAndCategoriesSame(dataForCompare: DataForCompare): Boolean =
        dataForCompare.filtersFromLocalStorage.size == dataForCompare.categoriesFromServer.size

    companion object {
        data class DataForCompare(
            val filtersFromLocalStorage: LinkedHashMap<String, Boolean>,
            val categoriesFromServer: List<String>
        )
    }
}