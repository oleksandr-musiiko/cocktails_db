package com.omusiiko.coctaildb.usecase

import com.omusiiko.coctaildb.repository.CocktailDBRepository
import io.reactivex.Single
import javax.inject.Inject

class GetListOfCategoriesFromServerUseCase @Inject constructor(private val repository: CocktailDBRepository) :
    Function0<Single<List<String>>> {

    override fun invoke(): Single<List<String>> =
        repository.getCategories()

}
