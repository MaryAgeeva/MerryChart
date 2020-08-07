package com.mary.merrychart.charts

import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import androidx.annotation.ColorInt
import com.mary.merrychart.utils.sp

internal abstract class EntriesRenderer<T: Entry> {

    internal var entriesList : List<T> = listOf()
    internal var valueFormatter : ((Double) -> String)? = null

    protected val entryPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    protected val entryTextPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
        textSize = DEFAULT_ENTRY_TEXT_SIZE.sp
    }

    internal fun setDefaultTextColor(@ColorInt color: Int) {
        entryTextPaint.color = color
    }

    internal fun setDefaultTextSize(size: Float) {
        entryTextPaint.textSize = size
    }

    internal fun setValueColor(@ColorInt color: Int) {
        entryPaint.color = color
    }

    internal abstract fun draw(
        canvas: Canvas,
        gridMinValue: Double,
        gridMaxValue: Double,
        gridStartX: Float,
        gridEndX: Float,
        gridStartY: Float,
        gridEndY: Float
    )

    protected companion object {
        const val DEFAULT_ENTRY_TEXT_SIZE = 10f
    }
}