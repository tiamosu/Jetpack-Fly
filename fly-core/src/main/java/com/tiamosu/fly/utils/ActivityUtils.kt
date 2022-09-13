package com.tiamosu.fly.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import java.lang.ref.WeakReference

/**
 * @author ti
 * @date 2022/9/13.
 */
object ActivityUtils {

    /**
     * [Activity] 是否处于活动状态
     */
    fun isActivityAlive(activity: Activity?): Boolean {
        return (activity != null
                && !activity.isFinishing) && !activity.isDestroyed
    }

    /**
     * 通过上下文获取 [Activity]
     */
    fun getActivityByContext(context: Context?): Activity? {
        if (context == null) return null
        val activity = getActivityByContextInner(context)
        return if (!isActivityAlive(activity)) null else activity
    }

    private fun getActivityByContextInner(context: Context?): Activity? {
        var ctx = context ?: return null
        val list = ArrayList<Context>()
        while (ctx is ContextWrapper) {
            if (ctx is Activity) {
                return ctx
            }
            val activity = getActivityFromDecorContext(ctx)
            if (activity != null) return activity
            list.add(ctx)
            ctx = ctx.baseContext ?: return null
            if (list.contains(ctx)) {
                return null
            }
        }
        return null
    }

    @Suppress("UNCHECKED_CAST")
    private fun getActivityFromDecorContext(context: Context?): Activity? {
        if (context == null) return null
        if (context.javaClass.name == "com.android.internal.policy.DecorContext") {
            kotlin.runCatching {
                val mActivityContextField = context.javaClass.getDeclaredField("mActivityContext")
                mActivityContextField.isAccessible = true
                return (mActivityContextField[context] as? WeakReference<Activity>)?.get()
            }
        }
        return null
    }
}