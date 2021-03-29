package com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.diffutils

import com.omusiiko.coctaildb.core.base.recyclerview.BaseDiffUtils
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterDisplayItem

class FiltersDisplayItemDiffUtils(
    private val oldList: List<FilterDisplayItem>,
    private val newList: List<FilterDisplayItem>
) : BaseDiffUtils<FilterDisplayItem>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].uniqueId == newList[newItemPosition].uniqueId
}


