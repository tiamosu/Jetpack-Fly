package com.tiamosu.fly.delegate

import android.view.View
import android.view.Window
import com.tiamosu.fly.FlySupportDialogFragment

/**
 * @author ti
 * @date 2022/7/15.
 */
interface IFlySupportDialogFragment {

    /**
     * 主题
     */
    fun bindTheme(): Int = View.NO_ID

    /**
     * 布局id
     */
    fun bindLayout(): Int

    /**
     * 初始化视图
     * 执行于[FlySupportDialogFragment.onViewCreated]之后
     */
    fun initView(dialog: FlySupportDialogFragment, contentView: View)

    /**
     * Dialog Window 配置
     */
    fun setWindowStyle(window: Window) {}

    /**
     * Dialog 取消
     */
    fun onDialogCancel(dialog: FlySupportDialogFragment) {}

    /**
     * Dialog 消失
     */
    fun onDialogDismiss(dialog: FlySupportDialogFragment) {}
}