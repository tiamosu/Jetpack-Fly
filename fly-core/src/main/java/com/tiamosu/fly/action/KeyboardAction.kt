package com.tiamosu.fly.action

import android.app.Activity
import android.view.View
import android.view.Window
import com.tiamosu.fly.utils.KeyboardUtils

/**
 * @author ti
 * @date 2022/7/6.
 */
interface KeyboardAction {

    /**
     * 显示软键盘
     */
    fun showKeyboard() {
        KeyboardUtils.showSoftInput()
    }

    /**
     * 显示软键盘
     */
    fun showKeyboard(activity: Activity) {
        KeyboardUtils.showSoftInput(activity)
    }

    /**
     * 显示软键盘
     */
    fun showKeyboard(view: View) {
        KeyboardUtils.showSoftInput(view)
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(view: View) {
        KeyboardUtils.hideSoftInput(view)
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(activity: Activity) {
        KeyboardUtils.hideSoftInput(activity)
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(window: Window) {
        KeyboardUtils.hideSoftInput(window)
    }

    /**
     * 切换软键盘
     */
    fun toggleSoftInput() {
        KeyboardUtils.toggleSoftInput()
    }
}