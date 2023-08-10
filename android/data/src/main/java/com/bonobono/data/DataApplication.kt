package com.bonobono.data

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson

class DataApplication : Application() {

    companion object {
        const val FILE_NAME = "with_me"
        lateinit var pref: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

        private val gson = Gson()
    }

    override fun onCreate() {
        super.onCreate()
        pref = applicationContext.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        editor = pref.edit()
    }

}