package com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model

import androidx.annotation.IntDef

sealed class DrinkDisplayItem {

    @Type
    abstract val id: Int

    abstract val uniqueId: String

    companion object {

        @IntDef(
            HEADER,
            DIVIDER,
            DRINK
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class Type

        const val HEADER = 0
        const val DIVIDER = 1
        const val DRINK = 2
    }

}

data class HeaderItem(val header: String) : DrinkDisplayItem() {
    override val id: Int =
        HEADER
    override val uniqueId: String
        get() = id.toString()
}

data class DividerItem(val nothing: Any) : DrinkDisplayItem() {
    override val id: Int =
        DIVIDER
    override val uniqueId: String
        get() = id.toString()
}

data class DrinkInfoItem(
    val name: String,
    val photo: String
) : DrinkDisplayItem() {
    override val id: Int =
        DRINK
    override val uniqueId: String
        get() = name + photo
}