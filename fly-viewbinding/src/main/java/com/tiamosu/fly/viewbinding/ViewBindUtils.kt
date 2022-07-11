package com.tiamosu.fly.viewbinding

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty

/**
 * @author ti
 * @date 2022/7/8.
 */
object ViewBindUtils {

    /**
     * Utility to find root view for ViewBinding in Activity
     */
    fun findRootView(activity: Activity): View? {
        val contentView = activity.findViewById<ViewGroup>(android.R.id.content)
        return if (contentView.isNotEmpty()) contentView.getChildAt(0) else null
    }
}