package com.mary.merrychart.charts.bar_chart

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import com.mary.merrychart.charts.AnimationManager

internal class BarAnimationManager : AnimationManager {

    private val animatorsList : List<Animator> = mutableListOf<ValueAnimator>()
    private var animatorSet = AnimatorSet()

    internal var shouldAnimateYAxis = false

    override fun animate() {
        animatorSet.apply {
            playSequentially(animatorsList)
            start()
        }
    }
}