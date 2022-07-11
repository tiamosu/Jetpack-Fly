package com.tiamosu.fly.kts

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

/**
 * @author tiamosu
 * @date 2022/7/8
 */

/**
 * 防止重复点击事件，默认0.5秒内不可重复点击
 */
fun View.clickNoRepeat(
    interval: Long = 500,
    block: (view: View) -> Unit
) {
    setOnClickListener {
        if (isValid(interval = interval)) {
            block.invoke(it)
        }
    }
}

/**
 * 防止重复点击事件，默认0.5秒内不可重复点击
 */
fun clickNoRepeat(
    interval: Long = 500,
    views: Array<out View?>,
    block: (view: View) -> Unit
) {
    views.forEach {
        it?.clickNoRepeat(interval, block)
    }
}

/**
 * 视图变化监听
 */
inline fun View.globalLayoutListener(crossinline onGlobalLayoutListener: () -> Unit) {
    val vto = viewTreeObserver
    if (!vto.isAlive) {
        return
    }
    vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        @Suppress("DEPRECATION")
        override fun onGlobalLayout() {
            val currentVto = viewTreeObserver
            if (currentVto.isAlive) {
                currentVto.removeOnGlobalLayoutListener(this)
            }
            onGlobalLayoutListener()
        }
    })
}

/**
 * 设置控件外间距
 */
inline fun View.updateMarginLayoutParams(block: ViewGroup.MarginLayoutParams.() -> Unit) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        block.invoke(this)
        layoutParams = this
    }
}