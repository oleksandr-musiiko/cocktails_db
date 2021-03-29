package com.omusiiko.coctaildb.applicationscreens.fragment.filters.viewmodel.model

interface FilterFlagChangedListener {

    fun flagOfFilterChanged(filter: String, flag: Boolean)

}