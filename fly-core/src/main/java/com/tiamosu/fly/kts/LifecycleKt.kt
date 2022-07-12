package com.tiamosu.fly.kts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ActivityUtils
import com.tiamosu.fly.FlySupportFragment

/**
 * @author ti
 * @date 2022/7/8.
 */

/**
 * 获取有效的生命周期所有者
 */
inline val LifecycleOwner.lifecycleOwnerEx: LifecycleOwner
    get() = when (this) {
        is Fragment -> {
            try {
                this.viewLifecycleOwner
            } catch (e: IllegalStateException) {
                this
            }
        }
        else -> this
    }

/**
 * 获取上下文
 */
inline val LifecycleOwner.context: FragmentActivity
    get() = when (this) {
        is FlySupportFragment -> context
        is Fragment -> checkNotNull(ActivityUtils.getActivityByContext(context) as? FragmentActivity)
        else -> checkNotNull(this as? FragmentActivity)
    }