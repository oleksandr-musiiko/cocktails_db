package com.omusiiko.coctaildb.repository.string

import android.content.Context

class StringRepositoryImpl constructor(
    private val context: Context
) : StringRepository {

    override fun getString(stringRes: Int): String = context.getString(stringRes)

    override fun getString(stringRes: Int, vararg formatArgs: Any?): String =
        context.getString(stringRes, *formatArgs)

}