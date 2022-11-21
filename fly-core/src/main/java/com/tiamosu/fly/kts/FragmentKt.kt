package com.tiamosu.fly.kts

import androidx.fragment.app.Fragment
import com.tiamosu.fly.FlySupportFragment

/**
 * @author ti
 * @date 2022/7/7.
 */

/**
 * 判断 Fragment 是否存活
 */
inline val Fragment.isFragmentAlive: Boolean
    get() = isAdded && !isDetached

/**
 * 延迟加载处理回调，[FlySupportFragment.onLazyLoad] 之后触发
 */
fun FlySupportFragment.launchWhenLazyResumed(callback: () -> Unit) {
    launchWhenLazyResumedHandle { callback.invoke() }
}