package com.bonobono.di

import android.app.Application
import com.bonobono.data.DataApplication
import com.bonobono.data.local.PreferenceDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}