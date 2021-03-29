package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.BaseViewModel
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.interactor.DrinksInteractor
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.mapper.DrinksListMapper
import com.omusiiko.coctaildb.retrofit.entity.drink.Drink
import com.omusiiko.coctaildb.repository.string.StringRepository
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// TESTED WITH MOCKITO in src\test\java\com\omusiiko\coctaildb\DrinksViewModelTest.kt
class DrinksViewModel @Inject constructor(
    private val interactor: DrinksInteractor,
    private val mapper: DrinksListMapper,
    private val stringRepository: StringRepository
) : BaseViewModel() {

    private var _drinksDisplaysItemsForAdd = MutableLiveData<List<DrinkDisplayItem>>()
    val drinksDisplayItemsForAdd: LiveData<List<DrinkDisplayItem>>
        get() = _drinksDisplaysItemsForAdd

    private var _visibilityTextViewNoDrinks =
        MutableLiveData<Boolean>()
    val visibilityTextViewNoDrinks: LiveData<Boolean>
        get() = _visibilityTextViewNoDrinks

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var isOpenToDownload = true

    private val linkedHashSetForDownload = linkedSetOf<String>()

    fun getCategories() {
        interactor.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ categories ->
                _drinksDisplaysItemsForAdd.value = null
                linkedHashSetForDownload.clear()
                setLinkedHashSetForDownload(categories)
                loadNextCategoryDrinksList()
            }, { e ->
                _message.value = stringRepository.getString(R.string.error_message, e.message)
                Log.e("ERROR", e.message, e)
            })
            .run(compositeDisposable::add)
    }

    fun loadNextCategoryDrinksList() {
        if (isOpenToDownload) {
            isOpenToDownload = false
            getDrinksFromOurListOfCategories()
        }
    }

    private fun setLinkedHashSetForDownload(categories: LinkedHashMap<String, Boolean>) {
        categories.forEach {
            if (it.value) {
                linkedHashSetForDownload.add(it.key)
            }
        }
    }

    private fun getDrinksFromOurListOfCategories() {
        if (linkedHashSetForDownload.isNotEmpty()) {
            addDrinksOfCurrentCategoryToResponseModel(linkedHashSetForDownload.first())
        } else {
            isOpenToDownload = true
        }
    }

    private fun addDrinksOfCurrentCategoryToResponseModel(category: String) {
        interactor.getDrinksByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ drinksOfCurrentCategory ->
                if (_visibilityTextViewNoDrinks.value == null) {
                    _visibilityTextViewNoDrinks.value = drinksOfCurrentCategory.isEmpty()
                }
                saveResultToDrinks(category, drinksOfCurrentCategory)
                linkedHashSetForDownload.remove(category)
                isOpenToDownload = true
            }, { e ->
                isOpenToDownload = true
                _message.value =
                    stringRepository.getString(R.string.error_message, e.message)
                Log.e("ERROR", e.message, e)
            })
            .run(compositeDisposable::add)
    }

    private fun saveResultToDrinks(
        category: String,
        listOfDrinks: List<Drink>
    ) {
        _drinksDisplaysItemsForAdd.value =
            mapper.convertToListOfDisplayItems(category, listOfDrinks)
    }
}