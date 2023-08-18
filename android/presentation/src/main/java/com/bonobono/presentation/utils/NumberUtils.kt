package com.bonobono.presentation.utils

import java.text.DecimalFormat

object NumberUtils {
    fun makeCommaExp(num: Int): String {
        var comma = DecimalFormat("#,###")
        return "${comma.format(num)} Exp"
    }
    fun makeCommaP(num: Int): String {
        var comma = DecimalFormat("#,###")
        return "${comma.format(num)} P"
    }
}