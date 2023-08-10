package com.bonobono.data

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

private const val TAG = "싸피"

class DataApplication : Application() {

    companion object {
        const val FILE_NAME = "with_me"
        lateinit var pref: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        private val gson = Gson()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: data application11!!!!")
        pref = applicationContext.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        editor = pref.edit()
        Log.d(TAG, "onCreate: data application22!!!!")
    }
}