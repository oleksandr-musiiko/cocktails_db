package com.omusiiko.coctaildb.core.base.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<Base>(
    private val adapterDelegatesManager: AdapterDelegatesManager<Base>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listOfItems = ArrayList<Base>()

    override fun getItemCount(): Int = listOfItems.size

    override fun getItemViewType(position: Int): Int =
        adapterDelegatesManager.getItemViewType(listOfItems, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        adapterDelegatesManager.onCreateViewHolder(parent, viewType).apply {
            adapterDelegatesManager.setOnClickListener(listOfItems, this, viewType)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        adapterDelegatesManager.onBindViewHolder(listOfItems, position, holder)

    open fun setItems(newArrayList: MutableList<Base>) {
        listOfItems.clear()
        listOfItems.addAll(newArrayList)
    }
}