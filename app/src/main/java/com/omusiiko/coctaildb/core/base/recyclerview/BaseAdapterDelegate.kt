package com.omusiiko.coctaildb.core.base.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapterDelegate<B, I, VH> {

    abstract fun isForViewType(items: List<B>, position: Int): Boolean

    abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    @Suppress("UNCHECKED_CAST")
    fun prepareViewHolder(
        items: List<B>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payload: MutableList<Any>? = null
    ) {
        val itemHolder: VH = holder as VH
        val item: I = items[position] as I

        if (payload.isNullOrEmpty()) onBindViewHolder(item, itemHolder)
        else onBindViewHolder(item, holder, payload)

    }

    abstract fun onBindViewHolder(item: I, holder: VH)

    open fun onBindViewHolder(item: I, holder: VH, payload: MutableList<Any>) {

    }

    open fun setOnClickListener(holder: RecyclerView.ViewHolder, item: List<B>) {

    }
}
