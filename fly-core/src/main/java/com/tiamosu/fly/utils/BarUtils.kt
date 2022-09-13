package com.tiamosu.fly.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import com.tiamosu.fly.kts.windowManager

/**
 * @author ti
 * @date 2022/9/13.
 */
object BarUtils {
    /**
     * 状态栏高度标识位
     */
    private const val IMMERSION_STATUS_BAR_HEIGHT = "status_bar_height"

    /**
     * 导航栏竖屏高度标识位
     */
    private const val IMMERSION_NAVIGATION_BAR_HEIGHT = "navigation_bar_height"

    /**
     * 导航栏横屏高度标识位
     */
    private const val IMMERSION_NAVIGATION_BAR_HEIGHT_LANDSCAPE = "navigation_bar_height_landscape"

    /**
     * 获取导航栏高度
     */
    fun getNavBarHeight(context: Context): Int {
        if (!isSupportNavBar()) return 0
        return getNavigationBarHeightInternal(context)
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        return getInternalDimensionSize(context, IMMERSION_STATUS_BAR_HEIGHT)
    }

    private fun getNavigationBarHeightInternal(context: Context): Int {
        val key =
            if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                IMMERSION_NAVIGATION_BAR_HEIGHT
            } else {
                IMMERSION_NAVIGATION_BAR_HEIGHT_LANDSCAPE
            }
        return getInternalDimensionSize(context, key)
    }

    private fun getInternalDimensionSize(context: Context, key: String): Int {
        kotlin.runCatching {
            val resourceId = Resources.getSystem().getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                val sizeOne = context.resources.getDimensionPixelSize(resourceId)
                val sizeTwo = Resources.getSystem().getDimensionPixelSize(resourceId)
                return if (sizeTwo >= sizeOne
                    && !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && key != IMMERSION_STATUS_BAR_HEIGHT)
                ) {
                    sizeTwo
                } else {
                    val densityOne = context.resources.displayMetrics.density
                    val densityTwo = Resources.getSystem().displayMetrics.density
                    val f = sizeOne * densityTwo / densityOne
                    (if (f >= 0) f + 0.5f else f - 0.5f).toInt()
                }
            }
        }
        return 0
    }

    /**
     * 判断是否支持导航栏
     */
    fun isSupportNavBar(): Boolean {
        val display = windowManager?.defaultDisplay ?: return false
        val size = Point()
        val realSize = Point()
        display.getSize(size)
        display.getRealSize(realSize)
        return realSize.y != size.y || realSize.x != size.x
    }
}