package com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.viewmodel.model.FilterFlagChangedListener
import com.omusiiko.coctaildb.core.base.recyclerview.BaseAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterItem
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.viewholder.FilterInfoViewHolder

class FilterInfoAdapterDelegate(private val flagChangedListener: FilterFlagChangedListener) :
    BaseAdapterDelegate<FilterDisplayItem, FilterItem, FilterInfoViewHolder>() {
    override fun isForViewType(
        items: List<FilterDisplayItem>,
        position: Int
    ): Boolean =
        items[position].id == FilterDisplayItem.FILTER

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        FilterInfoViewHolder.from(parent, flagChangedListener)

    override fun onBindViewHolder(
        item: FilterItem,
        holder: FilterInfoViewHolder
    ) =
        holder.bind(item)

}
