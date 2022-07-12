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

    //设置布局视图
    fun setContentView(): View?

    //初始化
    fun initActivity()

    //点击空白区域，默认隐藏软键盘
    fun clickBlankArea()
}