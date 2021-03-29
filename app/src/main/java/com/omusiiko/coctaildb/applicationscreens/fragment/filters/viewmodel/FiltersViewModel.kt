package com.omusiiko.coctaildb.applicationscreens.fragment.filters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.BaseViewModel
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.interactor.FiltersInteractor
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.mapper.FiltersListMapper
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.viewmodel.model.FilterFlagChangedListener
import com.omusiiko.coctaildb.repository.string.StringRepository
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterDisplayItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
    private val interactor: FiltersInteractor,
    private val mapper: FiltersListMapper,
    private val stringRepository: StringRepository
) : BaseViewModel(), FilterFlagChangedListener {

    private var _filters = MutableLiveData<List<FilterDisplayItem>>()
    val filters: LiveData<List<FilterDisplayItem>>
        get() = _filters

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var _visibilityTextViewNoFilters =
        MutableLiveData<Boolean>()
    val visibilityTextViewNoFilters: LiveData<Boolean>
        get() = _visibilityTextViewNoFilters

    private lateinit var filtersDataForUpdateToLocalStorage: LinkedHashMap<String, Boolean>

    fun getFiltersFromLocalStorage() {
        interactor.getFiltersFromLocalStorage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ filters ->
                _visibilityTextViewNoFilters.value = filters.isEmpty()
                filtersDataForUpdateToLocalStorage = filters
                _filters.value = mapper.convertToListOfDisplayItems(filters)
            }, { e ->
                Log.e("ERROR", e.message, e)
                _message.value =
                    stringRepository.getString(R.string.error_message, e.message)
            })
            .run(compositeDisposable::add)
    }

    fun updateFiltersSettingsToLocalStorage() {
        if (this::filtersDataForUpdateToLocalStorage.isInitialized) {
            interactor.saveFiltersToLocalStorage(filtersDataForUpdateToLocalStorage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _message.value =
                        stringRepository.getString(R.string.filters_settings_was_update)
                }, { e ->
                    Log.e("ERROR", e.message, e)
                    _message.value =
                        stringRepository.getString(R.string.error_message, e.message)
                })
                .run(compositeDisposable::add)
        }
    }

    override fun flagOfFilterChanged(filter: String, flag: Boolean) {
        if (this::filtersDataForUpdateToLocalStorage.isInitialized) {
            filtersDataForUpdateToLocalStorage[filter] = flag
        }
    }
}