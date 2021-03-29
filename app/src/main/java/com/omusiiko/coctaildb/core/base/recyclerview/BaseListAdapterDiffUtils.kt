package com.omusiiko.coctaildb.core.base.recyclerview

import androidx.recyclerview.widget.DiffUtil

abstract class BaseListAdapterDiffUtils<Base>(
    adapterDelegatesManager: AdapterDelegatesManager<Base>
) : BaseListAdapter<Base>(adapterDelegatesManager) {

    override fun setItems(newArrayList: MutableList<Base>) {
        getDiffUtilsResult(newArrayList).dispatchUpdatesTo(this)
        super.setItems(newArrayList)
    }

    abstract fun getDiffUtilsResult(list: MutableList<Base>): DiffUtil.DiffResult

}