package com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.omusiiko.coctaildb.core.base.recyclerview.AdapterDelegatesManager
import com.omusiiko.coctaildb.core.base.recyclerview.BaseListAdapterDiffUtils
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.diffutils.FiltersDisplayItemDiffUtils
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterDisplayItem

class FiltersListAdapterDiffUtils(
    adapterDelegatesManager:
    AdapterDelegatesManager<FilterDisplayItem>
) : BaseListAdapterDiffUtils<FilterDisplayItem>(adapterDelegatesManager) {

    override fun getDiffUtilsResult(list: MutableList<FilterDisplayItem>): DiffUtil.DiffResult =
        DiffUtil.calculateDiff(
            FiltersDisplayItemDiffUtils(
                listOfItems,
                list
            )
        )

}