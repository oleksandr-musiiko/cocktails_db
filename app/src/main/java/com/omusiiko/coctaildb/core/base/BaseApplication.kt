package com.omusiiko.coctaildb.core.base

import android.app.Application
import com.omusiiko.coctaildb.core.di.component.ApplicationComponent
import com.omusiiko.coctaildb.core.di.component.DaggerApplicationComponent
import com.omusiiko.coctaildb.core.di.module.ContextModule

class BaseApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }
}
