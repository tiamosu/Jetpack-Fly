package com.tiamosu.fly.delegate

import android.view.View
import com.tiamosu.fly.action.BundleAction
import com.tiamosu.fly.action.HandlerAction
import com.tiamosu.fly.action.KeyboardAction
import com.tiamosu.fly.action.ScenesAction

/**
 * @author ti
 * @date 2022/7/12.
 */
interface IFlySupportActivity : ScenesAction,
    BundleAction, KeyboardAction, HandlerAction, IFlyBackCallback {

    /**
     * 初始化，置于[setContentView] 之前
     */
    fun initCreate() {}

    //设置布局视图
    fun setContentView(): View?

    /**
     * 初始化，置于[setContentView] 之后
     */
    fun initActivity()

    //点击空白区域，默认隐藏软键盘
    fun clickBlankArea()
}