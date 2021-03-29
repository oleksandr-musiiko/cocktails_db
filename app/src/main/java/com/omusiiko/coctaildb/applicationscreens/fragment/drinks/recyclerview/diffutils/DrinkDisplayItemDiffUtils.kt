package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.diffutils

import com.omusiiko.coctaildb.core.base.recyclerview.BaseDiffUtils
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem

class DrinkDisplayItemDiffUtils(
    private val oldList: List<DrinkDisplayItem>,
    private val newList: List<DrinkDisplayItem>
) : BaseDiffUtils<DrinkDisplayItem>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].uniqueId == newList[newItemPosition].uniqueId

}


