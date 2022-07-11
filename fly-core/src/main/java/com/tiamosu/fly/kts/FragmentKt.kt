package com.tiamosu.fly.kts

import androidx.fragment.app.Fragment

/**
 * @author ti
 * @date 2022/7/7.
 */

/**
 * 判断 Fragment 是否存活
 */
inline val Fragment.isFragmentAlive: Boolean
    get() = isAdded && !isDetached