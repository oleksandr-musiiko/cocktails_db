package com.omusiiko.coctaildb.usecase

import com.omusiiko.coctaildb.repository.CocktailDBRepository
import com.omusiiko.coctaildb.retrofit.entity.drink.Drink
import io.reactivex.Single
import javax.inject.Inject

class GetDrinksByCategoryUseCase @Inject constructor(private val repository: CocktailDBRepository) :
    Function1<GetDrinksByCategoryUseCase.Request, Single<List<Drink>>> {

    override fun invoke(request: Request): Single<List<Drink>> =
        repository.getDrinksByCategory(request.category)

    data class Request(val category: String)
}
