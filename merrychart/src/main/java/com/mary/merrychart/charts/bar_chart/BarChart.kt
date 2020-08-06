package com.mary.merrychart.charts.bar_chart

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.withStyledAttributes
import com.mary.merrychart.R
import com.mary.merrychart.utils.color
import com.mary.merrychart.utils.dp
import com.mary.merrychart.utils.sp
import kotlin.system.measureTimeMillis

class BarChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val chartRenderer = BarChartRenderer(context)

    init {
        attrs?.let {
            getAttributesFromXML(it)
        }
    }

    fun configure(config: Config) {
        config.list?.let { list ->
            chartRenderer.setEntriesList(list)
        }
        config.valueFormatter?.let { formatter ->
            chartRenderer.setValueFormatter(formatter)
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("BarChart", "renders: ${measureTimeMillis {
            canvas?.let {
                chartRenderer.draw(
                    it,
                    paddingStart.toFloat(),
                    paddingTop.toFloat(),
                    paddingEnd.toFloat(),
                    paddingBottom.toFloat()
                )
            }
        }
        }")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        /*setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            (MeasureSpec.getSize(heightMeasureSpec) / 2)
        )*/
    }

    private fun getAttributesFromXML(attrs: AttributeSet) {
        context.withStyledAttributes(
            attrs,
            R.styleable.BarChart
        ) {
            chartRenderer.setEmptyText(
                getString(
                    R.styleable.BarChart_emptyDataText
                )?: context.getString(DEFAULT_EMPTY_TEXT)
            )
            chartRenderer.setEntriesWidth(
                getDimension(
                    R.styleable.BarChart_entryWidth,
                    DEFAULT_ENTRY_WIDTH.dp.toFloat()
                )
            )
            chartRenderer.setValueColor(
                getColor(
                    R.styleable.BarChart_entryColor,
                    context.color(DEFAULT_ENTRY_COLOR)
                )
            )
            chartRenderer.setValueTextColor(
                getColor(
                    R.styleable.BarChart_entryTextColor,
                    context.color(DEFAULT_TEXT_COLOR)
                )
            )
            chartRenderer.setXAxisTextColor(
                getColor(
                    R.styleable.BarChart_xAxisTextColor,
                    context.color(DEFAULT_TEXT_COLOR)
                )
            )
            chartRenderer.setYAxisTextColor(
                getColor(
                    R.styleable.BarChart_yAxisTextColor,
                    context.color(DEFAULT_SECONDARY_TEXT_COLOR)
                )
            )
            chartRenderer.setValueTextSize(
                getDimension(
                    R.styleable.BarChart_entryTextSize,
                    DEFAULT_ENTRY_TEXT_SIZE.sp
                )
            )
            chartRenderer.setXAxisTextSize(
                getDimension(
                    R.styleable.BarChart_xAxisTextSize,
                    DEFAULT_ENTRY_TEXT_SIZE.sp
                )
            )
            chartRenderer.setYAxisTextSize(
                getDimension(
                    R.styleable.BarChart_yAxisTextSize,
                    DEFAULT_ENTRY_TEXT_SIZE.sp
                )
            )
            chartRenderer.setGridColor(
                getColor(
                    R.styleable.BarChart_gridColor,
                    context.color(DEFAULT_GRID_COLOR)
                )
            )
        }
    }

    companion object {
        private val DEFAULT_ENTRY_COLOR = R.color.blue
        private val DEFAULT_GRID_COLOR = R.color.grey
        private val DEFAULT_TEXT_COLOR = R.color.primary_text
        private val DEFAULT_SECONDARY_TEXT_COLOR = R.color.secondary_text
        private val DEFAULT_EMPTY_TEXT = R.string.no_data

        private const val DEFAULT_ENTRY_WIDTH = 8f
        private const val DEFAULT_ENTRY_TEXT_SIZE = 10f
    }

    class Config private constructor(
        internal val list: List<BarChartEntry>? = null,
        internal val valueFormatter: ((Double) -> String)? = null
    ) {
        class Builder {
            private var list: List<BarChartEntry>? = null
            private var valueFormatter: ((Double) -> String)? = null

            fun setList(list: List<BarChartEntry>) = apply {
                this.list = list
            }

            fun setValueFormatter(valueFormatter: (Double) -> String) = apply {
                this.valueFormatter = valueFormatter
            }

            fun build() : Config {
                return Config(
                    list,
                    valueFormatter
                )
            }
        }
    }
}