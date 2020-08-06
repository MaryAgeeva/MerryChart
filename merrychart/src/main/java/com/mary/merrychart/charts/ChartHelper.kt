package com.mary.merrychart.charts

private const val DEFAULT_MIN_DIFF = 10

internal fun createGridValues(
    minValue: Double,
    maxValue: Double,
    areNegativeValuesAllowed: Boolean = false
) : List<Double> {
    return when {
        maxValue > minValue && (maxValue - minValue) > 10 -> {
            val midValue = (maxValue + minValue) / 2
            listOf(
                maxValue + (maxValue - midValue),
                maxValue,
                midValue,
                minValue,
                minValue - (midValue - minValue))
        }
        maxValue > minValue -> {
            val diff = maxValue - minValue
            listOf(
                maxValue + diff,
                maxValue,
                minValue,
                minValue - diff,
                minValue - 2 * diff
            )
        }
        else -> {
            listOf(
                minValue + 2 * DEFAULT_MIN_DIFF,
                minValue + DEFAULT_MIN_DIFF,
                minValue,
                minValue - DEFAULT_MIN_DIFF,
                minValue - 2 * DEFAULT_MIN_DIFF
            )
        }
    }
}