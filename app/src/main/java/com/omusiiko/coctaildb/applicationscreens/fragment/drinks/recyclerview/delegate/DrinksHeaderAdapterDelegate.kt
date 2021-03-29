package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omusiiko.coctaildb.core.base.recyclerview.BaseAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.HeaderItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.viewholder.DrinkCategoryHeaderViewHolder

class DrinksHeaderAdapterDelegate :
    BaseAdapterDelegate<DrinkDisplayItem, HeaderItem, DrinkCategoryHeaderViewHolder>() {

    override fun isForViewType(
        items: List<DrinkDisplayItem>,
        position: Int
    ): Boolean =
        items[position].id == DrinkDisplayItem.HEADER

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        DrinkCategoryHeaderViewHolder.from(parent)

    override fun onBindViewHolder(item: HeaderItem, holder: DrinkCategoryHeaderViewHolder) =
        holder.bind(item)

}
