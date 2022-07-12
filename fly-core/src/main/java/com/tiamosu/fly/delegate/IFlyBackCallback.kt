package com.tiamosu.fly.delegate

/**
 * @author ti
 * @date 2022/7/12.
 */
fun interface IFlyBackCallback {

    //页面回退处理
    fun onBackPressedSupport(): Boolean
}