package com.omusiiko.coctaildb.core.base.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterDelegatesManager<Base> {

    private val arrayDelegates: ArrayList<BaseAdapterDelegate<Base, *, *>> = ArrayList()

    fun addDelegate(adapterDelegate: BaseAdapterDelegate<Base, *, *>) =
        arrayDelegates.add(adapterDelegate)

    fun getItemViewType(items: List<Base>, position: Int): Int {

        for ((index, delegate) in arrayDelegates.withIndex()) {

            if (delegate.isForViewType(items, position)) {
                return index
            }
        }

        throw NullPointerException("No AdapterDelegate added that matches position $position in data source")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val delegate: BaseAdapterDelegate<Base, *, *>? = arrayDelegates[viewType]

        delegate?.let {
            return it.onCreateViewHolder(parent)
        }

        throw NullPointerException("No AdapterDelegate added for ViewType $viewType")
    }


    fun onBindViewHolder(items: List<Base>, position: Int, holder: RecyclerView.ViewHolder) {
        val delegate: BaseAdapterDelegate<Base, *, *>? = arrayDelegates[holder.itemViewType]

        delegate?.let {
            return it.prepareViewHolder(items, position, holder)

        }

        throw NullPointerException("No AdapterDelegate added for position $position")

    }

    fun onBindViewHolder(
        items: List<Base>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payload: MutableList<Any>
    ) {
        val delegate: BaseAdapterDelegate<Base, *, *>? = arrayDelegates[holder.itemViewType]

        delegate?.let {
            return if (payload.isEmpty()) it.prepareViewHolder(items, position, holder)
            else it.prepareViewHolder(items, position, holder, payload)

        }

        throw NullPointerException("No AdapterDelegate added for position $position")

    }

    fun setOnClickListener(
        listOfItems: ArrayList<Base>,
        holder: RecyclerView.ViewHolder,
        viewType: Int
    ) =
        arrayDelegates[viewType].setOnClickListener(holder, listOfItems)

}
