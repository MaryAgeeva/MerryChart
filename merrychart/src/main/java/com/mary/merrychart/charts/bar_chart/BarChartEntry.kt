package com.mary.merrychart.charts.bar_chart

import androidx.annotation.ColorInt
import com.mary.merrychart.charts.Entry

data class BarChartEntry (
    val position: Int,
    val value: Double,
    val xLabel: String? = null,
    @ColorInt val color: Int = 0
) : Entry