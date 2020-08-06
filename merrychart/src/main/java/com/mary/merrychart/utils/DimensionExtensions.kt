package com.mary.merrychart.utils

import android.content.res.Resources

internal val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

internal val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

internal val Int.sp: Float
    get() = this * Resources.getSystem().displayMetrics.scaledDensity

internal val Float.sp: Float
    get() = this * Resources.getSystem().displayMetrics.scaledDensity
