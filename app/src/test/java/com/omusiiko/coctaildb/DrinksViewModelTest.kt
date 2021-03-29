package com.omusiiko.coctaildb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.interactor.DrinksInteractor
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.mapper.DrinksListMapper
import com.omusiiko.coctaildb.retrofit.entity.drink.Drink
import com.omusiiko.coctaildb.repository.string.StringRepository
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.viewmodel.DrinksViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DrinksViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    private val drinksInteractor: DrinksInteractor =
        mock()
    private val drinksListMapper: DrinksListMapper =
        mock()
    private val stringRepository: StringRepository =
        mock()

    private val mockCategoryKeyFirst = "category"
    private val mockCategoryKeySecond = "category2"
    private val mockCategoryValue = true
    private val mockCategoryLinkedHashMap =
        linkedMapOf(
            mockCategoryKeyFirst to mockCategoryValue,
            mockCategoryKeySecond to mockCategoryValue
        )

    private val mockOfDrink = mock<Drink>()
    private val mockListOfDrinks = listOf(mockOfDrink)
    private val mockListOfDrinksSize = mockListOfDrinks.size

    private val mockOfDrinkDisplayItem =
        mock<DrinkDisplayItem>()
    private val mockListOfDrinkDisplayItem = listOf(mockOfDrinkDisplayItem)

    private lateinit var drinksViewModel: DrinksViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        drinksViewModel =
            DrinksViewModel(
                drinksInteractor,
                drinksListMapper,
                stringRepository
            )
    }

    @Test
    fun getCategories_error() {
        Mockito.`when`(drinksInteractor.getCategories())
            .thenAnswer {
                Single.error<Exception>(Exception())
            }

        drinksViewModel.getCategories()

        Mockito.verify(drinksInteractor).getCategories()

        Assert.assertEquals(
            drinksViewModel.message.value,
            stringRepository.getString(R.string.error_message, Exception::class.java.simpleName)
        )
        Assert.assertNull(drinksViewModel.drinksDisplayItemsForAdd.value)
        Assert.assertNull(drinksViewModel.visibilityTextViewNoDrinks.value)
    }

    @Test
    fun getCategories_success() {
        Mockito.`when`(drinksInteractor.getCategories())
            .thenReturn(Single.just(mockCategoryLinkedHashMap))

        drinksViewModel.getCategories()

        Mockito.verify(drinksInteractor).getCategories()

        Assert.assertNull(drinksViewModel.message.value)
        Assert.assertNull(drinksViewModel.drinksDisplayItemsForAdd.value)
        Assert.assertNull(drinksViewModel.visibilityTextViewNoDrinks.value)
    }

    @Test
    fun loadNextCategoryDrinksList_error() {
        Mockito.`when`(drinksInteractor.getCategories())
            .thenReturn(Single.just(mockCategoryLinkedHashMap))
        Mockito.`when`(drinksInteractor.getDrinksByCategory(mockCategoryKeyFirst))
            .thenAnswer {
                Single.error<Exception>(Exception())
            }

        drinksViewModel.getCategories()
        drinksViewModel.loadNextCategoryDrinksList()

        Mockito.verify(drinksInteractor).getCategories()
        Mockito.verify(drinksInteractor, Mockito.times(2)).getDrinksByCategory(mockCategoryKeyFirst)

        Assert.assertEquals(
            drinksViewModel.message.value,
            stringRepository.getString(R.string.error_message, Exception::class.java.simpleName)
        )
        Assert.assertNull(drinksViewModel.drinksDisplayItemsForAdd.value)
        Assert.assertNull(drinksViewModel.visibilityTextViewNoDrinks.value)
    }

    @Test
    fun loadNextCategoryDrinksList_success() {
        Mockito.`when`(drinksInteractor.getCategories())
            .thenReturn(Single.just(mockCategoryLinkedHashMap))
        Mockito.`when`(drinksInteractor.getDrinksByCategory(mockCategoryKeyFirst))
            .thenReturn(Single.just(mockListOfDrinks))
        Mockito.`when`(drinksInteractor.getDrinksByCategory(mockCategoryKeySecond))
            .thenReturn(Single.just(mockListOfDrinks))
        Mockito.`when`(
            drinksListMapper.convertToListOfDisplayItems(
                mockCategoryKeyFirst,
                mockListOfDrinks
            )
        ).thenReturn(mockListOfDrinkDisplayItem.toMutableList())
        Mockito.`when`(
            drinksListMapper.convertToListOfDisplayItems(
                mockCategoryKeySecond,
                mockListOfDrinks
            )
        ).thenReturn(mockListOfDrinkDisplayItem.toMutableList())

        drinksViewModel.getCategories()
        drinksViewModel.loadNextCategoryDrinksList()

        Mockito.verify(drinksInteractor).getCategories()
        Mockito.verify(drinksInteractor).getDrinksByCategory(mockCategoryKeyFirst)
        Mockito.verify(drinksInteractor).getDrinksByCategory(mockCategoryKeySecond)

        Assert.assertNull(drinksViewModel.message.value)
        Assert.assertEquals(
            drinksViewModel.drinksDisplayItemsForAdd.value?.size,
            mockListOfDrinkDisplayItem.size
        )
        Assert.assertEquals(drinksViewModel.visibilityTextViewNoDrinks.value, false)
    }

}
