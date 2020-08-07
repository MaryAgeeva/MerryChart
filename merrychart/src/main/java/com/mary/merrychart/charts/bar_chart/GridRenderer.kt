package com.mary.merrychart.charts.bar_chart

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint
import androidx.annotation.ColorInt
import com.mary.merrychart.charts.Grid
import com.mary.merrychart.charts.GridValues
import com.mary.merrychart.charts.createGridValues
import com.mary.merrychart.utils.dp
import com.mary.merrychart.utils.empty
import com.mary.merrychart.utils.sp
import com.mary.merrychart.utils.toQuantityString

internal class GridRenderer {

    internal var gridValues: GridValues? = null
        private set
    internal var grid: Grid? = null
        private set

    internal var emptyText = String.empty()

    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = DEFAULT_GRID_LINE_THICKNESS.dp.toFloat()
        style = Paint.Style.STROKE
    }

    private val xAxisTextPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
        textSize = DEFAULT_X_TEXT_SIZE.sp
    }

    private val yAxisTextPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
        textSize = DEFAULT_Y_TEXT_SIZE.sp
    }

    internal fun setGridColor(@ColorInt color: Int) {
        gridPaint.color = color
    }

    internal fun setXTextColor(@ColorInt color: Int) {
        xAxisTextPaint.color = color
    }

    internal fun setYTextColor(@ColorInt color: Int) {
        yAxisTextPaint.color = color
    }

    internal fun setXTextSize(size: Float) {
        xAxisTextPaint.textSize = size
    }

    internal fun setYTextSize(size: Float) {
        yAxisTextPaint.textSize = size
    }

    internal fun createValues(
        minValue: Double,
        maxValue: Double
    ) {
        val values = createGridValues(minValue, maxValue)
        gridValues = GridValues(
            values = values,
            min = values.min()?: 0.0,
            max = values.max()?: 0.0
        )
    }

    internal fun createGrid(
        canvas: Canvas,
        paddingStart: Float,
        paddingTop: Float,
        paddingEnd: Float,
        paddingBottom: Float
    ) {
        val rowHeight = (canvas.height - paddingTop - paddingBottom - xAxisTextPaint.textSize
                - X_AXIS_TEXT_PADDING.dp * 2) / 5
        val maxValueLength = yAxisTextPaint.measureText((gridValues?.max?: 0.0).toQuantityString())
        val textYCenter = (yAxisTextPaint.textSize + (yAxisTextPaint.descent() + yAxisTextPaint.ascent()) / 2) / 2

        grid = Grid(
            startX = paddingStart + maxValueLength + Y_AXIS_TEXT_PADDING.dp,
            startY = paddingTop + Y_AXIS_TEXT_PADDING.dp - textYCenter,
            endX = canvas.width - paddingEnd,
            endY = paddingTop + Y_AXIS_TEXT_PADDING.dp - textYCenter + (rowHeight * 4),
            rowHeight = rowHeight,
            textYCenter = textYCenter,
            maxTextLength = maxValueLength
        )
    }

    internal fun drawEmptyGrid (
        canvas: Canvas,
        paddingStart: Float,
        paddingTop: Float,
        paddingEnd: Float,
        paddingBottom: Float
    ) {
        canvas.drawRect(
            RectF(
                paddingStart,
                paddingTop,
                (canvas.width - paddingEnd),
                (canvas.height - paddingBottom)
            ),
            gridPaint
        )

        val textLength = xAxisTextPaint.measureText(emptyText)
        val xText = ((canvas.width - paddingStart - paddingEnd) / 2 + paddingStart) - (textLength / 2)
        val yText = ((canvas.height - paddingTop - paddingBottom) / 2 + paddingTop)
        + (xAxisTextPaint.textSize / 2)

        canvas.drawText(
            emptyText,
            xText,
            yText,
            xAxisTextPaint
        )
        grid = null
        gridValues = null
    }

    internal fun drawGrid (
        canvas: Canvas,
        paddingStart: Float,
        paddingTop: Float,
        paddingEnd: Float,
        paddingBottom: Float
    ) {
        grid?.let { entityGrid ->
            gridValues?.values?.forEachIndexed { index, value ->
                val textLength = yAxisTextPaint.measureText(value.toQuantityString())
                val xDiff = if(entityGrid.maxTextLength > textLength)
                    (entityGrid.maxTextLength - textLength) / 2
                else 0f

                canvas.drawText(
                    value.toQuantityString(),
                    (paddingStart + xDiff),
                    (paddingTop + Y_AXIS_TEXT_PADDING.dp + (entityGrid.rowHeight * index)),
                    yAxisTextPaint
                )

                val lineY = paddingTop + Y_AXIS_TEXT_PADDING.dp - entityGrid.textYCenter + (entityGrid.rowHeight * index)
                canvas.drawLine(
                    entityGrid.startX,
                    lineY,
                    entityGrid.endX,
                    lineY,
                    gridPaint
                )
            }
        }
    }

    private companion object {
        private const val DEFAULT_GRID_LINE_THICKNESS = 0.5f
        private const val DEFAULT_X_TEXT_SIZE = 10f
        private const val DEFAULT_Y_TEXT_SIZE = 10f

        private const val X_AXIS_TEXT_PADDING = 8f
        private const val Y_AXIS_TEXT_PADDING = 8f
    }
}