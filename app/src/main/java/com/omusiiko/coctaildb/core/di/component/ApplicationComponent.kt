package com.omusiiko.coctaildb.core.di.component

import com.omusiiko.coctaildb.core.base.BaseActivity
import com.omusiiko.coctaildb.core.base.BaseFragment
import com.omusiiko.coctaildb.core.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)
}
