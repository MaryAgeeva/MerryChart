package com.mary.merrychart.utils

import android.content.Context
import androidx.core.content.ContextCompat
import java.util.*

private const val DECIMAL_FORMAT = "%1$,.1f"

internal fun String.Companion.empty() = ""

internal fun Context.color(res: Int) = ContextCompat.getColor(this, res)

internal fun Double.toQuantityString(decimalFormat: String = DECIMAL_FORMAT) : String {
    return if(this - this.toInt() == 0.0) this.toInt().toString()
    else toDecimalString(decimalFormat)
}

private fun Double.toDecimalString(decimalFormat: String = DECIMAL_FORMAT) : String =
    String.format(Locale.getDefault(), decimalFormat, this)