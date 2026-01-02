package com.example.myfirebase.util

import java.text.NumberFormat
import java.util.Locale

object FormatUtil {
    fun formatCurrency(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(amount)
    }
    
    fun capitalizeFirstLetter(text: String): String {
        return text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}
