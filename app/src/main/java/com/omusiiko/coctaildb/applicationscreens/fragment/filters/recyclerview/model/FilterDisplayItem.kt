package com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model

import androidx.annotation.IntDef

sealed class FilterDisplayItem {

    @Type
    abstract val id: Int

    abstract val uniqueId: String

    companion object {

        @IntDef(
            FILTER
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class Type

        const val FILTER = 0
    }

}

data class FilterItem(
    val name: String,
    val flag: Boolean
) : FilterDisplayItem() {
    override val id: Int =
        FILTER
    override val uniqueId: String
        get() = name + flag
}