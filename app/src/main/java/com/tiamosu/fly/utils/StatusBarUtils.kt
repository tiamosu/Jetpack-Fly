package com.tiamosu.fly.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.blankj.utilcode.util.Utils

/**
 * @author ti
 * @date 2022/7/11.
 */
object StatusBarUtils {
    private const val STATUS_BAR_HEIGHT = "status_bar_height"

    /**
     * 获取状态栏高度（px）
     */
    fun getStatusBarHeight(context: Context = Utils.getApp()): Int {
        return getInternalDimensionSize(context)
    }

    private fun getInternalDimensionSize(context: Context, key: String = STATUS_BAR_HEIGHT): Int {
        kotlin.runCatching {
            val resourceId = Resources.getSystem().getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                val sizeOne = context.resources.getDimensionPixelSize(resourceId)
                val sizeTwo = Resources.getSystem().getDimensionPixelSize(resourceId)
                return if (sizeTwo >= sizeOne
                    && !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && key != STATUS_BAR_HEIGHT)
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
}