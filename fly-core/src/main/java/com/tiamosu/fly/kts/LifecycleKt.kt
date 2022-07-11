package com.tiamosu.fly.kts

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

/**
 * @author ti
 * @date 2022/7/8.
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