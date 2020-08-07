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
                paddingBottom
            )
            gridRenderer.drawGrid(
                canvas,
                paddingStart,
                paddingTop,
                paddingEnd,
                paddingBottom
            )
            gridRenderer.grid?.let { grid ->
                entriesRenderer.draw(
                    canvas,
                    grid.startX,
                    grid.endX,
                    grid.startY,
                    grid.endY
                )
            }
        }
    }

    override fun setXAxisTextColor(@ColorInt color: Int) {
        (entriesRenderer as? BarEntriesRenderer)?.setXTextColor(color)
    }

    override fun setXAxisTextSize(size: Float) {
        (entriesRenderer as? BarEntriesRenderer)?.setXTextSize(size)
    }

    internal fun setEntriesWidth(width: Float) {
        (entriesRenderer as? BarEntriesRenderer)?.entryWidth = width
    }

    override fun setEntriesList(list: List<BarChartEntry>) {
        gridRenderer.createValues(
            (list.minBy { it.value }?.value?: 0.0),
            (list.maxBy { it.value }?.value?: 0.0)
        )
        (entriesRenderer as? BarEntriesRenderer)?.setList(
            list,
            (gridRenderer.gridValues?.min?: 0.0),
            (gridRenderer.gridValues?.max?: 0.0)
        )
    }
}