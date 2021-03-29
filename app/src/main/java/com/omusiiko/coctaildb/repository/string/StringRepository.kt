package com.omusiiko.coctaildb.repository.string

import androidx.annotation.StringRes

interface StringRepository {

    fun getString(@StringRes stringRes: Int): String

    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any?): String

}