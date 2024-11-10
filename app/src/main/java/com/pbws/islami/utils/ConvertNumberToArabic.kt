package com.pbws.islami.utils

import java.text.NumberFormat
import java.util.Locale

fun convertToArabicNumerals(time: String): String {
    val arabicNumberFormat = NumberFormat.getInstance(Locale("ar", "EG"))

    return time.map { char ->
        if (char.isDigit()) {
            arabicNumberFormat.format(char.toString().toInt())
        } else {
            char.toString()
        }
    }.joinToString("")
}