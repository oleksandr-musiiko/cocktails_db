package com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.viewholder

import android.view.View
import android.view.ViewGroup
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.viewmodel.model.FilterFlagChangedListener
import com.omusiiko.coctaildb.core.base.recyclerview.BaseViewHolder
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterItem
import com.omusiiko.coctaildb.common.utils.inflate
import kotlinx.android.synthetic.main.holder_filter_info.view.*

class FilterInfoViewHolder(
    view: View,
    private val filterFlagChangedListener: FilterFlagChangedListener
) : BaseViewHolder<FilterItem>(view) {

    private val filterNameTextView = view.textView_nameOfFilter_filterHolder
    private val flagOfFilterCheckBox = view.checkbox_nameOfFilter_filterHolder

    override fun bind(item: FilterItem) {
        setViews(item)
    }

    private fun setViews(item: FilterItem) {
        filterNameTextView.text = item.name
        setCheck(item)
        setFilterFlagChangedListener(item)
    }

    private fun setCheck(item: FilterItem) {
        flagOfFilterCheckBox.isChecked = item.flag
    }

    private fun setFilterFlagChangedListener(item: FilterItem) {
        flagOfFilterCheckBox.setOnCheckedChangeListener { _, isChecked ->
            filterFlagChangedListener.flagOfFilterChanged(
                item.name,
                isChecked
            )
        }
    }

    companion object {
        fun from(
            parent: ViewGroup, filterFlagChangedListener: FilterFlagChangedListener
        ) =
            FilterInfoViewHolder(
                parent.inflate(R.layout.holder_filter_info),
                filterFlagChangedListener
            )
    }
}