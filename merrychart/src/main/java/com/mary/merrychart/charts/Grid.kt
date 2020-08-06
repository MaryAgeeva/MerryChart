package com.mary.merrychart.charts

internal data class Grid(
    val startX: Float,
    val startY: Float,
    val endX: Float,
    val endY: Float,
    val rowHeight: Float,
    val textYCenter: Float,
    val maxTextLength: Float,
    val minValue: Double,
    val maxValue: Double,
    val values: List<Double>
)