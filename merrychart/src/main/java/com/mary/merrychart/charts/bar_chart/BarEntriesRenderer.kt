package com.mary.merrychart.charts.bar_chart

import android.content.Context
import android.graphics.Canvas
import android.text.TextPaint
import androidx.annotation.ColorInt
import com.mary.merrychart.R
import com.mary.merrychart.charts.EntriesRenderer
import com.mary.merrychart.utils.color
import com.mary.merrychart.utils.dp
import com.mary.merrychart.utils.sp
import com.mary.merrychart.utils.toQuantityString

internal class BarEntriesRenderer(
    private val context: Context
) : EntriesRenderer<BarChartEntry>() {

    private val entriesMap: HashMap<BarChartEntry, Double> = hashMapOf()

    internal var entryWidth = DEFAULT_ENTRY_WIDTH.dp.toFloat()

    private val xAxisTextPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
        textSize = DEFAULT_X_TEXT_SIZE.sp
    }

    internal fun setList(
        list: List<BarChartEntry>,
        gridMinValue: Double,
        gridMaxValue: Double
    ) {
        entriesList = list.sortedBy { it.position }
        calculateEntriesDiff(gridMinValue, gridMaxValue)
    }

    internal fun setXTextColor(@ColorInt color: Int) {
        xAxisTextPaint.color = color
    }

    internal fun setXTextSize(size: Float) {
        xAxisTextPaint.textSize = size
    }

    override fun calculateEntriesDiff(
        gridMinValue: Double,
        gridMaxValue: Double
    ) {
        entriesMap.clear()
        entriesList.forEach { entry ->
            entriesMap[entry] = (entry.value - gridMinValue) / (gridMaxValue - gridMinValue)
        }
    }

    override fun draw(
        canvas: Canvas,
        gridStartX: Float,
        gridEndX: Float,
        gridStartY: Float,
        gridEndY: Float
    ) {
        val visibleEntriesCount = if(entriesList.size > VISIBLE_ENTRIES_LIMIT)
            VISIBLE_ENTRIES_LIMIT
        else entriesList.size

        val columnWidth = (gridEndX - gridStartX) / visibleEntriesCount
        if(entryWidth >= columnWidth)
            entryWidth = columnWidth
        val startColumnOffset = (columnWidth / 2) - (entryWidth / 2)

        entriesList.forEachIndexed { position, entry ->
            entry.draw(
                canvas,
                (gridStartX + startColumnOffset + position * columnWidth),
                gridStartY,
                gridEndY
            )
        }
    }

    private fun BarChartEntry.draw(
        canvas: Canvas,
        offset: Float,
        gridYMin: Float,
        gridYMax: Float
    ) {
        if(color != 0)
            entryPaint.color = color
        else if(color == 0 && entryPaint.color != context.color(DEFAULT_ENTRY_COLOR))
            entryPaint.color = context.color(DEFAULT_ENTRY_COLOR)

        val diff = entriesMap[this]?: 0.0
        val entryHeight = (gridYMax - gridYMin) * diff
        val text = valueFormatter?.invoke(value)?: value.toQuantityString()
        val textLength = entryTextPaint.measureText(text)

        canvas.drawText(
            text,
            offset - ((textLength - entryWidth) / 2),
            (gridYMax - entryHeight - ENTRY_TEXT_PADDING.dp).toFloat(),
            entryTextPaint
        )

        canvas.drawRoundRect(
            offset,
            (gridYMax - entryHeight).toFloat(),
            offset + entryWidth,
            gridYMax,
            DEFAULT_RADIUS.dp.toFloat(),
            DEFAULT_RADIUS.dp.toFloat(),
            entryPaint
        )

        val xLabel = xLabel?: position.toString()
        val xLabelLength = xAxisTextPaint.measureText(xLabel)

        canvas.drawText(
            xLabel,
            offset - ((xLabelLength - entryWidth) / 2),
            gridYMax + X_AXIS_TEXT_PADDING.dp + xAxisTextPaint.textSize,
            xAxisTextPaint
        )
    }

    private companion object {
        private val DEFAULT_ENTRY_COLOR = R.color.blue

        private const val DEFAULT_X_TEXT_SIZE = 10f
        private const val X_AXIS_TEXT_PADDING = 8f
        private const val ENTRY_TEXT_PADDING = 6f

        private const val DEFAULT_RADIUS = 8f

        private const val DEFAULT_ENTRY_WIDTH = 8f

        private const val VISIBLE_ENTRIES_LIMIT = 10
    }
}