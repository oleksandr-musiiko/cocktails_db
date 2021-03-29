package com.omusiiko.coctaildb.applicationscreens.fragment.filters.mapper

import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterItem
import javax.inject.Inject

class FiltersListMapper @Inject constructor() {

    fun convertToListOfDisplayItems(filters: LinkedHashMap<String, Boolean>): MutableList<FilterDisplayItem> =
        ArrayList<FilterDisplayItem>().apply {

            filters.forEach { filter ->
                add(
                    FilterItem(
                        name = filter.key,
                        flag = filter.value
                    )
                )
            }

        }

}
