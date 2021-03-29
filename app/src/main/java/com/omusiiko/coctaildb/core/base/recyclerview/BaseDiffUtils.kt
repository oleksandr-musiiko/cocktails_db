package com.omusiiko.coctaildb.core.base.recyclerview

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtils<Base>(
    private val oldList: List<Base>,
    private val newList: List<Base>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}
