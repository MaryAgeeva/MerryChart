package com.mary.merrychart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mary.merrychart.charts.bar_chart.BarChart
import com.mary.merrychart.charts.bar_chart.BarChartEntry
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_bar_chart.configure(BarChart.Config.Builder()
            .setList(
                listOf(
                    BarChartEntry(
                        position = 0,
                        value = 35.0,
                        xLabel = "First",
                        color = this@MainActivity.color(R.color.red)
                    ),
                    BarChartEntry(
                        position = 1,
                        value = 25.0,
                        xLabel = "Second",
                        color = this@MainActivity.color(R.color.yellow)
                    ),
                    BarChartEntry(
                        position = 2,
                        value = 20.0,
                        xLabel = "Third",
                        color = this@MainActivity.color(R.color.green)
                    ),
                    BarChartEntry(
                        position = 3,
                        value = 25.0,
                        xLabel = "Fourth"
                    ),
                    BarChartEntry(
                        position = 4,
                        value = 30.0,
                        xLabel = "Fifth",
                        color = this@MainActivity.color(R.color.colorAccent)
                    )
                )
            )
            .build())
    }
}
