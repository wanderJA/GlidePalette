package com.github.florent37.glidepalette.sample

import android.util.TypedValue
import android.view.View

fun View.dpToPx(dpValue: Float): Float {
    val dm = context.resources.displayMetrics
    val scale = dm.density
    return dpValue * scale + 0.5f
}

fun View.dp2Px(dpValue: Float): Int {
    val dm = context.resources.displayMetrics
    val scale = dm.density
    return (dpValue * scale + 0.5f).toInt()
}

fun View.sp2px(spValue: Float): Float {
    val dm = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spValue,dm)
}