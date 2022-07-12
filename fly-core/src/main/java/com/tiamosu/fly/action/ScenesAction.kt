package com.tiamosu.fly.action

import android.content.Context
import android.os.Bundle
import com.tiamosu.fly.FlySupportActivity
import com.tiamosu.fly.FlySupportFragment

/**
 * @author ti
 * @date 2022/7/6.
 */
interface ScenesAction {

    /**
     * 上下文
     */
    fun getContext(): Context

    /**
     * 布局Id
     */
    fun getLayoutId(): Int

    /**
     * 初始化参数，如：页面传参
     * 执行于[FlySupportActivity.onCreate] or [FlySupportFragment.onViewCreated]
     */
    fun initParameter(bundle: Bundle?)

    /**
     * 初始化视图
     * 执行于[initParameter]之后
     */
    fun initView()

    /**
     * 初始化事件，如：点击事件
     * 执行于[initView]之后
     */
    fun initEvent()

    /**
     * 初始化观察者，如：LiveData观察者
     * 执行于[initEvent]之后
     */
    fun initObserver()

    /**
     * 加载数据
     * 执行于[initObserver]之后
     */
    fun loadData()

    /**
     * 懒加载处理，页面可见时执行
     * 若是[FlySupportFragment]，并且保证在转场动画执行之后[FlySupportFragment.onCreateAnimation]
     */
    fun onLazyLoad()
}