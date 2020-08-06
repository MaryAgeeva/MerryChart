package com.mary.merrychart.charts

import android.graphics.Canvas
import androidx.annotation.ColorInt
import com.mary.merrychart.charts.bar_chart.GridRenderer

internal abstract class ChartRenderer<T: Entry> (
    protected val entriesRenderer: EntriesRenderer<T>
) {
    internal var width: Float = 0f
    internal var heigth: Float = 0f

    protected var gridRenderer = GridRenderer()

    internal abstract fun draw(
        canvas: Canvas,
        paddingStart: Float,
        paddingTop: Float,
        paddingEnd: Float,
        paddingBottom: Float
    )

    internal abstract fun setEntriesList(list: List<T>)

    internal fun setGridColor(@ColorInt color: Int) {
        gridRenderer.setGridColor(color)
    }

    internal fun setValueTextColor(@ColorInt color: Int) {
        entriesRenderer.setDefaultTextColor(color)
    }

    internal open fun setXAxisTextColor(@ColorInt color: Int) {
        gridRenderer.setXTextColor(color)
    }

    internal fun setYAxisTextColor(@ColorInt color: Int) {
        gridRenderer.setYTextColor(color)
    }

    internal fun setValueTextSize(size: Float) {
        entriesRenderer.setDefaultTextSize(size)
    }

    internal open fun setXAxisTextSize(size: Float) {
        gridRenderer.setXTextSize(size)
    }

    internal fun setYAxisTextSize(size: Float) {
        gridRenderer.setYTextSize(size)
    }

    internal fun setValueFormatter(formatter: (Double) -> String) {
        entriesRenderer.valueFormatter = formatter
    }

    internal fun setEmptyText(text: String) {
        gridRenderer.emptyText = text
    }

    internal fun setValueColor(@ColorInt color: Int) {
        entriesRenderer.setValueColor(color)
    }
}