package com.omusiiko.coctaildb.core.di.module

import androidx.lifecycle.ViewModel
import com.omusiiko.coctaildb.core.base.ViewModelFactory
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.interactor.DrinksInteractor
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.interactor.FiltersInteractor
import com.omusiiko.coctaildb.applicationscreens.fragment.checkforfilters.interactor.InitCategoriesInteractor
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.mapper.DrinksListMapper
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.mapper.FiltersListMapper
import com.omusiiko.coctaildb.repository.string.StringRepository
import com.omusiiko.coctaildb.applicationscreens.fragment.checkforfilters.viewmodel.CheckForFiltersViewModel
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.viewmodel.DrinksViewModel
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.viewmodel.FiltersViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module(includes = [ContextModule::class, RepositoryModule::class])
class ViewModelModule {

    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(
        providerMap: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory =
        ViewModelFactory(providerMap)

    @Provides
    @IntoMap
    @ViewModelKey(DrinksViewModel::class)
    fun bindDrinksViewModel(
        interactor: DrinksInteractor,
        mapper: DrinksListMapper,
        stringRepository: StringRepository
    ): ViewModel =
        DrinksViewModel(
            interactor,
            mapper,
            stringRepository
        )

    @Provides
    @IntoMap
    @ViewModelKey(FiltersViewModel::class)
    fun bindFiltersViewModel(
        interactor: FiltersInteractor,
        mapper: FiltersListMapper,
        stringRepository: StringRepository
    ): ViewModel =
        FiltersViewModel(
            interactor,
            mapper,
            stringRepository
        )

    @Provides
    @IntoMap
    @ViewModelKey(CheckForFiltersViewModel::class)
    fun bindCheckForFiltersViewModel(
        interactor: InitCategoriesInteractor,
        stringRepository: StringRepository
    ): ViewModel =
        CheckForFiltersViewModel(
            interactor,
            stringRepository
        )
}