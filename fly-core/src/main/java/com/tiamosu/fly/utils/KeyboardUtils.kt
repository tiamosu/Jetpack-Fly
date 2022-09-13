package com.tiamosu.fly.utils

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ResultReceiver
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.tiamosu.fly.kts.appContext
import com.tiamosu.fly.kts.inputMethodManager
import kotlin.math.abs

/**
 * @author ti
 * @date 2022/9/13.
 */
object KeyboardUtils {
    private var sDecorViewDelta = 0

    /**
     * 显示软键盘
     */
    fun showSoftInput() {
        inputMethodManager?.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    /**
     * 显示软键盘
     */
    fun showSoftInput(activity: Activity?) {
        if (activity == null) return
        if (!isSoftInputVisible(activity)) {
            toggleSoftInput()
        }
    }

    /**
     * 显示软键盘
     */
    fun showSoftInput(view: View) {
        showSoftInput(view, 0)
    }

    /**
     * 显示软键盘
     */
    fun showSoftInput(view: View, flags: Int) {
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
        inputMethodManager?.showSoftInput(
            view, flags, object : ResultReceiver(Handler(Looper.getMainLooper())) {
                override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                    if (resultCode == InputMethodManager.RESULT_UNCHANGED_HIDDEN
                        || resultCode == InputMethodManager.RESULT_HIDDEN
                    ) {
                        toggleSoftInput()
                    }
                }
            })
        showSoftInput()
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput(view: View) {
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideSoftInput(activity: Activity?) {
        if (activity == null) return
        hideSoftInput(activity.window)
    }

    fun hideSoftInput(window: Window?) {
        if (window == null) return
        var view = window.currentFocus
        if (view == null) {
            val decorView = window.decorView
            val focusView = decorView.findViewWithTag<View>("keyboardTagView")
            if (focusView == null) {
                view = EditText(window.context)
                view.setTag("keyboardTagView")
                (decorView as ViewGroup).addView(view, 0, 0)
            } else {
                view = focusView
            }
            view.requestFocus()
        }
        hideSoftInput(view)
    }

    /**
     * 切换键盘显示与否状态
     */
    fun toggleSoftInput() {
        inputMethodManager?.toggleSoftInput(0, 0)
    }

    /**
     * 判断软键盘是否可见
     */
    fun isSoftInputVisible(activity: Activity): Boolean {
        return getDecorViewInvisibleHeight(activity.window) > 0
    }

    private fun getDecorViewInvisibleHeight(window: Window): Int {
        val decorView = window.decorView
        val outRect = Rect()
        decorView.getWindowVisibleDisplayFrame(outRect)
        val delta = abs(decorView.bottom - outRect.bottom)
        if (delta <= BarUtils.getNavBarHeight(appContext) + BarUtils.getStatusBarHeight(appContext)) {
            sDecorViewDelta = delta
            return 0
        }
        return delta - sDecorViewDelta
    }
}