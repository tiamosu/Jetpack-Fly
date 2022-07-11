package com.tiamosu.fly

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tiamosu.fly.action.BundleAction
import com.tiamosu.fly.action.HandlerAction
import com.tiamosu.fly.action.KeyboardAction
import com.tiamosu.fly.action.ScenesAction

/**
 * @author ti
 * @date 2022/7/6.
 */
abstract class FlySupportActivity : AppCompatActivity(),
    ScenesAction, BundleAction, KeyboardAction, HandlerAction {

    final override fun getContext() = this

    final override val bundle: Bundle?
        get() = intent.extras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        initActivity()
    }

    /**
     * 设置布局视图
     */
    protected open fun setContentView(): View? {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId())
        }
        return null
    }

    /**
     * 相关函数初始化
     */
    protected open fun initActivity() {
        initParameter(bundle)
        initView()
        initEvent()
        initObserver()
        loadData()

        lifecycleScope.launchWhenResumed { lazyLoadData() }
    }

    /**
     * 点击空白区域，默认隐藏软键盘
     */
    protected open fun clickBlankArea() {
        hideKeyboard(getContext())
    }

    override fun onDestroy() {
        removeCallbacks()
        super.onDestroy()
    }

    /**
     * 点击空白区域隐藏软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN
            && isShouldHideKeyboard(currentFocus, ev)
        ) {
            clickBlankArea()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isShouldHideKeyboard(view: View?, event: MotionEvent): Boolean {
        if (view is EditText) {
            val l = intArrayOf(0, 0)
            view.getLocationOnScreen(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + view.height
            val right = left + view.width
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false
    }
}
