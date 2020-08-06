package com.mary.merrychart.charts.bar_chart

import android.content.Context
import android.graphics.Canvas
import androidx.annotation.ColorInt
import com.mary.merrychart.charts.ChartRenderer

internal class BarChartRenderer(
    context: Context
) : ChartRenderer<BarChartEntry>(BarEntriesRenderer(context)) {

    override fun draw(
        canvas: Canvas,
        paddingStart: Float,
        paddingTop: Float,
        paddingEnd: Float,
        paddingBottom: Float
    ) {
        if(entriesRenderer.entriesList.isEmpty())
            gridRenderer.drawEmptyGrid(
                canvas,
                paddingStart,
                paddingTop,
                paddingEnd,
                paddingBottom
            )
        else {
            gridRenderer.createGrid(
                canvas,
                paddingStart,
                paddingTop,
                paddingEnd,
                paddingBottom,
                (entriesRenderer.entriesList.minBy { it.value }?.value?: 0.0),
                (entriesRenderer.entriesList.maxBy { it.value }?.value?: 0.0)
            )
            gridRenderer.drawGrid(
                canvas,
                paddingStart,
                paddingTop,
                paddingEnd,
                paddingBottom
            )
            entriesRenderer.draw(
                canvas,
                (gridRenderer.grid?.minValue?: 0.0),
                (gridRenderer.grid?.maxValue?: 0.0),
                (gridRenderer.grid?.startX?: 0f),
                (gridRenderer.grid?.endX?: 0f),
                (gridRenderer.grid?.startY?: 0f),
                (gridRenderer.grid?.endY?: 0f)
            )
        }
    }

    override fun setXAxisTextColor(@ColorInt color: Int) {
        (entriesRenderer as? BarEntriesRenderer)?.setXTextColor(color)
    }

    override fun setXAxisTextSize(size: Float) {
        (entriesRenderer as? BarEntriesRenderer)?.setXTextSize(size)
    }

    override fun setEntriesList(list: List<BarChartEntry>) {
        (entriesRenderer as? BarEntriesRenderer)?.setList(list)
    }

    internal fun setEntriesWidth(width: Float) {
        (entriesRenderer as? BarEntriesRenderer)?.entryWidth = width
    }
}