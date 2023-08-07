package com.bonobono.data

import android.app.Application
import android.content.SharedPreferences

class DataApplication: Application() {

    companion object {
        const val FILE_NAME = "with_me_preference"
        lateinit var pref: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

    }

    override fun onCreate() {
        super.onCreate()
        pref = applicationContext.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        editor = pref.edit()
    }
}