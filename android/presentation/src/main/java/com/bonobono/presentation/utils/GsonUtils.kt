package com.bonobono.presentation.utils

import com.google.gson.Gson

object GsonUtils {
    fun toJson(value: Any?) : String {
        return Gson().toJson(value)
    }

    inline fun <reified T> fromJson(value: String?) : T? {
        return runCatching {
            Gson().fromJson(value, T::class.java)
        }.getOrNull()
    }
}