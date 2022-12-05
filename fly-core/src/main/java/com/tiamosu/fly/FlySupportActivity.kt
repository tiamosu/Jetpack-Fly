package com.tiamosu.fly

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tiamosu.fly.delegate.ActivityResultDelegate
import com.tiamosu.fly.delegate.IFlyBackCallback
import com.tiamosu.fly.delegate.IFlySupportActivity
import com.tiamosu.fly.utils.FlySupportUtils

/**
 * @author ti
 * @date 2022/7/6.
 */
abstract class FlySupportActivity : AppCompatActivity(), IFlySupportActivity {
    val startDelegate = ActivityResultDelegate(apply { })

    final override fun getContext() = this

    final override val bundle: Bundle?
        get() = intent.extras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCreate()
    }

    @CallSuper
    override fun initCreate() {
        setContentView()
        initActivity()
    }

    /**
     * 设置布局视图
     */
    override fun setContentView(): View? {
        val layoutId = getLayoutId()
        if (layoutId > 0) {
            setContentView(layoutId)
        }
        return null
    }

    /**
     * 相关函数初始化
     */
    override fun initActivity() {
        initParameter(bundle)
        initView()
        initEvent()
        initObserver()
        loadData()

        lifecycleScope.launchWhenResumed { onLazyLoad() }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent)
    }

    /**
     * 点击空白区域，默认隐藏软键盘
     */
    override fun clickBlankArea() {
        hideKeyboard(this)
    }

    /**
     * 点击空白区域隐藏软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        onDispatchTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    open fun onDispatchTouchEvent(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN
            && isShouldHideKeyboard(currentFocus, event)
        ) {
            clickBlankArea()
        }
    }

    open fun isShouldHideKeyboard(view: View?, event: MotionEvent): Boolean {
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

    /**
     * 不建议重写该函数，请使用 [onBackPressedSupport] 代替
     */
    override fun onBackPressed() {
        val activeFragment = FlySupportUtils.getActiveFragment(supportFragmentManager)
        if (dispatchBackPressedEvent(activeFragment) || onBackPressedSupport()) {
            return
        }
        onBackPressedDispatcher.onBackPressed()
    }

    private fun dispatchBackPressedEvent(backCallback: IFlyBackCallback?): Boolean {
        if (backCallback != null) {
            if (backCallback.onBackPressedSupport()) {
                return true
            }
            val parentFragment = (backCallback as? Fragment)?.parentFragment
            return dispatchBackPressedEvent(parentFragment as? IFlyBackCallback)
        }
        return false
    }

    /**
     * 页面回退处理
     */
    override fun onBackPressedSupport() = false

    override fun onDestroy() {
        removeCallbacks()
        super.onDestroy()
    }
}
