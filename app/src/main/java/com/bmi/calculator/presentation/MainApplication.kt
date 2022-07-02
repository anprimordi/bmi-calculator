package com.bmi.calculator.presentation

import android.support.multidex.MultiDexApplication
import com.bmi.calculator.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}
