package com.omusiiko.coctaildb.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @Singleton
    @Provides
    fun getContext(): Context =
        this.context.applicationContext

}