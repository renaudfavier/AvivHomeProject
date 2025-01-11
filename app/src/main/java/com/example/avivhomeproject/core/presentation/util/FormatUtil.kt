package com.example.avivhomeproject.core.presentation.util

import kotlin.math.roundToInt

fun Double.formatWithSpaceBetweenThousands(): String {
    val integerPart = this.toInt()
    val afterComaPart = ((this - integerPart) * 100).roundToInt()

    val integerPartWithSpaces = integerPart.formatWithSpaceBetweenThousands()

    return when(afterComaPart) {
        0 -> integerPartWithSpaces
        in 1..9 -> "$integerPartWithSpaces,0$afterComaPart"
        else -> "$integerPartWithSpaces,$afterComaPart"
    }
}

fun Int.formatWithSpaceBetweenThousands(): String = when(this) {
    in 0.. 999 -> "$this"
    else -> {
        val stringRest = when(val rest = this%1000) {
            in 0..9 -> "00$rest"
            in 10 .. 99 -> "0$rest"
            else -> rest
        }
        "${(this/1000).formatWithSpaceBetweenThousands()} $stringRest"
    }
}
