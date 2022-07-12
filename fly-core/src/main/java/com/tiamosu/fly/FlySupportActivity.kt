package com.tiamosu.fly

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tiamosu.fly.delegate.ActivityResultDelegate
import com.tiamosu.fly.delegate.FlySupportActivityDelegate
import com.tiamosu.fly.delegate.IFlySupportActivity

/**
 * @author ti
 * @date 2022/7/6.
 */
abstract class FlySupportActivity : AppCompatActivity(), IFlySupportActivity {
    private val delegate by lazy { FlySupportActivityDelegate(this) }
    val startDelegate = ActivityResultDelegate(apply { })

    final override fun getContext() = this

    final override val bundle: Bundle?
        get() = intent.extras

    /**
     * 设置布局视图
     */
    override fun setContentView(): View? {
        return delegate.setContentView()
    }

    /**
     * 相关函数初始化
     */
    override fun initActivity() {
        delegate.initActivity()
    }

    /**
     * 点击空白区域，默认隐藏软键盘
     */
    override fun clickBlankArea() {
        delegate.clickBlankArea()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent)
    }

    /**
     * 点击空白区域隐藏软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        delegate.dispatchTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 不建议重写该函数，请使用 [onBackPressedSupport] 代替
     */
    override fun onBackPressed() {
        delegate.onBackPressed()
    }

    /**
     * 页面回退处理
     */
    override fun onBackPressedSupport() = false
}
