package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.omusiiko.coctaildb.core.base.recyclerview.AdapterDelegatesManager
import com.omusiiko.coctaildb.core.base.recyclerview.BaseListAdapterDiffUtils
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.diffutils.DrinkDisplayItemDiffUtils
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem

class DrinksListAdapterDiffUtils(
    adapterDelegatesManager:
    AdapterDelegatesManager<DrinkDisplayItem>
) : BaseListAdapterDiffUtils<DrinkDisplayItem>(adapterDelegatesManager) {

    override fun getDiffUtilsResult(list: MutableList<DrinkDisplayItem>): DiffUtil.DiffResult =
        DiffUtil.calculateDiff(
            DrinkDisplayItemDiffUtils(
                listOfItems,
                list
            )
        )

}