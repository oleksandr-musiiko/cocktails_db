package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.viewholder

import android.view.View
import android.view.ViewGroup
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.recyclerview.BaseViewHolder
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.HeaderItem
import com.omusiiko.coctaildb.common.utils.inflate
import kotlinx.android.synthetic.main.holder_drink_category_header_title.view.*

class DrinkCategoryHeaderViewHolder(view: View) : BaseViewHolder<HeaderItem>(view) {

    private val headerTextView = view.textView_headerTitle_holderDrinkCategory

    override fun bind(item: HeaderItem) {
        headerTextView.text = item.header
    }

    companion object {
        fun from(parent: ViewGroup) =
            DrinkCategoryHeaderViewHolder(
                parent.inflate(
                    R.layout.holder_drink_category_header_title
                )
            )
    }
}
