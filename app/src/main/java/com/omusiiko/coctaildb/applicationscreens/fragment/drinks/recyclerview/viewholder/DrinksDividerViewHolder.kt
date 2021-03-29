package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.viewholder

import android.view.View
import android.view.ViewGroup
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.recyclerview.BaseViewHolder
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DividerItem
import com.omusiiko.coctaildb.common.utils.inflate

class DrinksDividerViewHolder(view: View) : BaseViewHolder<DividerItem>(view) {

    override fun bind(item: DividerItem) {
        //NO-OP
    }

    companion object {
        fun from(parent: ViewGroup) =
            DrinksDividerViewHolder(
                parent.inflate(R.layout.holder_drinks_divider)
            )
    }
}
