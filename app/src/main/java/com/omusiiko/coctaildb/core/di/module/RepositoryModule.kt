package com.omusiiko.coctaildb.core.di.module

import android.content.Context
import com.omusiiko.coctaildb.repository.CocktailDBRepository
import com.omusiiko.coctaildb.repository.CocktailDBRepositoryImpl
import com.omusiiko.coctaildb.repository.FiltersRepository
import com.omusiiko.coctaildb.repository.LocalFiltersRepository
import com.omusiiko.coctaildb.repository.string.StringRepository
import com.omusiiko.coctaildb.repository.string.StringRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getStringRepository(context: Context):
            StringRepository = StringRepositoryImpl(context)

    @Singleton
    @Provides
    fun getLocalFiltersRepository(context: Context):
            FiltersRepository = LocalFiltersRepository(context)

    @Singleton
    @Provides
    fun getCocktailDBRepository():
            CocktailDBRepository = CocktailDBRepositoryImpl()
}