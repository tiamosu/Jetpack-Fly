package com.tiamosu.fly.delegate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tiamosu.fly.action.BundleAction
import com.tiamosu.fly.action.HandlerAction
import com.tiamosu.fly.action.KeyboardAction
import com.tiamosu.fly.action.ScenesAction

/**
 * @author ti
 * @date 2022/7/12.
 */
interface IFlySupportFragment : ScenesAction,
    BundleAction, KeyboardAction, HandlerAction, IFlyBackCallback {

    //设置布局视图
    fun setContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    //初始化
    fun initFragment()
}