package com.tiamosu.fly.delegate

/**
 * @author ti
 * @date 2022/9/14.
 */
class FlyVisibleDelegate(
    private val iFlySupport: IFlySupportFragment,
    private val fragmentDelegate: FlySupportFragmentDelegate
) {
    private var isSupportVisible = false

    fun onResume() {
        if (isSupportVisible) return
        isSupportVisible = true
        iFlySupport.onSupportVisible()
        fragmentDelegate.startLazyLoadData()
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
}