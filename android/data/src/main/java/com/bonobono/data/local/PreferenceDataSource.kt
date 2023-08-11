package com.bonobono.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceDataSource @Inject constructor(
    @ApplicationContext context: Context,
) {

    companion object {
        private const val PREFERENCE_NAME = "with_me"
        const val COMPLETED_OX_QUIZ_TIME = "ox"
        const val COMPLETED_FOUR_QUIZ_TIME = "four"
        const val COMPLETED_ATTENDANCE_TIME = "attendance"
    }

    private fun getPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    val prefs by lazy { getPreference(context) }
    private val editor by lazy { prefs.edit() }
    private val gson = Gson()

    fun putString(key: String, data: String?) {
        editor.putString(key, data)
        editor.apply()
    }

    fun putBoolean(key: String, data: Boolean) {
        editor.putBoolean(key, data)
        editor.apply()
    }

    fun putInt(key: String, data: Int) {
        editor.putInt(key, data)
        editor.apply()
    }

    fun putLong(key: String, data: Long) {
        editor.putLong(key, data)
        editor.apply()
    }

    fun getString(key: String, defValue: String? = null): String? {
        return prefs.getString(key, defValue)
    }

    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    fun getInt(key: String, defValue: Int = 0): Int {
        return prefs.getInt(key, defValue)
    }

    fun getLong(key: String, defValue: Long = 0) : Long {
        return prefs.getLong(key, defValue)
    }

    fun remove(key: String) {
        editor.remove(key)
        editor.apply()
    }

//   객체 저장 -> 유저 정보
//    fun putAccountInfo(accountInfo: AccountInfo) {
//        putString(ACCOUNT_INFO, gson.toJson(accountInfo))
//    }
//
//    fun getAccountInfo() : AccountInfo? {
//        return gson.fromJson(getString(ACCOUNT_INFO), AccountInfo::class.java)
//    }
//
//    fun removeAccountInfo() {
//        putString(ACCOUNT_INFO, null)
//    }

}