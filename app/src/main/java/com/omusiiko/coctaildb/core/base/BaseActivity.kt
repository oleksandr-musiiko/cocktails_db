package com.omusiiko.coctaildb.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes layout: Int) : AppCompatActivity(layout) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.application as BaseApplication).applicationComponent.inject(this)
    }
}
