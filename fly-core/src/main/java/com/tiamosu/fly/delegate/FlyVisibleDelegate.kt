package com.tiamosu.fly.delegate

import androidx.fragment.app.Fragment

/**
 * @author ti
 * @date 2022/9/14.
 */
class FlyVisibleDelegate(private val iFlySupport: IFlySupportFragment) {
    private val fragment by lazy { checkNotNull(iFlySupport as? Fragment) }
    private var isSupportVisible = false

    fun onResume() {
        if (!isFragmentVisible || isSupportVisible) return
        isSupportVisible = true
        iFlySupport.onSupportVisible()
    }

    fun onPause() {
        if (!isSupportVisible) return
        isSupportVisible = false
        iFlySupport.onSupportInvisible()
    }

    fun onDestroyView() {
        isSupportVisible = false
    }

    fun isSupportVisible() = isSupportVisible

    private val isFragmentVisible: Boolean
        get() = !fragment.isHidden
}