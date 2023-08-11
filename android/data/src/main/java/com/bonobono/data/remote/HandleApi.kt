package com.bonobono.data.remote

import android.util.Log
import com.bonobono.domain.model.NetworkResult

internal inline fun <T> handleApi(transform: () -> T): NetworkResult<T> = try {
    NetworkResult.Success(transform.invoke())
} catch (e: Exception) {
    when (e) {
        else -> e.message?.let {
            Log.d("TEST", "handleApi: ${e.message}")
            NetworkResult.Error(it)
        } ?: NetworkResult.Error("UnKnown Error Occured")
    }
}