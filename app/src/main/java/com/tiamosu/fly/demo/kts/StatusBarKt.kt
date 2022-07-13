package com.tiamosu.fly.demo.kts

import android.app.Activity
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.BarUtils
import com.tiamosu.fly.demo.base.BaseActivity
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.utils.StatusBarUtils

/**
 * @author ti
 * @date 2022/6/7.
 */

/**
 * 状态栏高度
 */
val statusBarHeight by lazy { StatusBarUtils.getStatusBarHeight() }

/**
 * 沉浸式状态栏
 */
fun LifecycleOwner.immersionBar(block: ImmersionBar.() -> Unit = {}) {
    fun applyConfig(activity: Activity) {
        ImmersionBar(activity)
            .apply(block)
            .build()
    }
    when (this) {
        is BaseActivity -> {
            applyConfig(this)
        }
        is BaseFragment -> {
            activity?.let { applyConfig(it) }
        }
    }
}

class ImmersionBar(private val activity: Activity) {
    private var isDarkFont: Boolean = false
    private var isVisibility: Boolean = true

    @ColorInt
    private var color: Int = Color.TRANSPARENT

    fun statusBarDarkFont(isDarkFont: Boolean): ImmersionBar {
        this.isDarkFont = isDarkFont
        return this
    }

    fun setBarVisibility(isVisibility: Boolean): ImmersionBar {
        this.isVisibility = isVisibility
        return this
    }

    fun setBarColor(@ColorInt color: Int): ImmersionBar {
        this.color = color
        return this
    }

    fun build() {
        BarUtils.setStatusBarVisibility(activity, isVisibility)
        BarUtils.setStatusBarLightMode(activity, isDarkFont)
        BarUtils.setStatusBarColor(activity, color, color != Color.TRANSPARENT)
    }
}