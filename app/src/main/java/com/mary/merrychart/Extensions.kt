package com.mary.merrychart

import android.content.Context
import androidx.core.content.ContextCompat

internal fun Context.color(res: Int) = ContextCompat.getColor(this, res)