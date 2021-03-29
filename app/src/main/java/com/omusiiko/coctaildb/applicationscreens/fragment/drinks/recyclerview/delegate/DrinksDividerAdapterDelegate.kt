package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omusiiko.coctaildb.core.base.recyclerview.BaseAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DividerItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.viewholder.DrinksDividerViewHolder

class DrinksDividerAdapterDelegate :
    BaseAdapterDelegate<DrinkDisplayItem, DividerItem, DrinksDividerViewHolder>() {

    override fun isForViewType(
        items: List<DrinkDisplayItem>,
        position: Int
    ): Boolean =
        items[position].id == DrinkDisplayItem.DIVIDER

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        DrinksDividerViewHolder.from(parent)

    override fun onBindViewHolder(item: DividerItem, holder: DrinksDividerViewHolder) =
        holder.bind(item)
}
