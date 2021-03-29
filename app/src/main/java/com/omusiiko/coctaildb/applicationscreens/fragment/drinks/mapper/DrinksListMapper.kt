package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.mapper

import com.omusiiko.coctaildb.retrofit.entity.drink.Drink
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkInfoItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.HeaderItem
import javax.inject.Inject

class DrinksListMapper @Inject constructor() {

    fun convertToListOfDisplayItems(
        category: String,
        listOfDrinks: List<Drink>
    ): MutableList<DrinkDisplayItem> =
        ArrayList<DrinkDisplayItem>().apply {

            add(
                HeaderItem(
                    header = category
                )
            )

            listOfDrinks.forEach { drink ->
                if (isValidDrink(drink)) {
                    add(
                        DrinkInfoItem(
                            name = drink.name,
                            photo = drink.photo
                        )
                    )
                }
            }

        }

    // needed because of an error while loading from CocktailDB
    private fun isValidDrink(drink: Drink) = drink.name != null && drink.photo != null
}
