package com.mary.merrychart.charts.bar_chart

import androidx.annotation.ColorInt

data class BarEntryObject(
    val position: Int,
    val value: Double,
    val xLabel: String? = null,
    @ColorInt val color: Int = 0
)