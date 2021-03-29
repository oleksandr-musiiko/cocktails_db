package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omusiiko.coctaildb.core.base.recyclerview.BaseAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkInfoItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.viewholder.DrinkInfoViewHolder

class DrinkInfoAdapterDelegate :
    BaseAdapterDelegate<DrinkDisplayItem, DrinkInfoItem, DrinkInfoViewHolder>() {
    override fun isForViewType(
        items: List<DrinkDisplayItem>,
        position: Int
    ): Boolean =
        items[position].id == DrinkDisplayItem.DRINK

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        DrinkInfoViewHolder.from(parent)

    override fun onBindViewHolder(
        item: DrinkInfoItem,
        holder: DrinkInfoViewHolder
    ) =
        holder.bind(item)

}
