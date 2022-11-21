package com.tiamosu.fly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tiamosu.fly.delegate.ActivityResultDelegate
import com.tiamosu.fly.delegate.FlySupportFragmentDelegate
import com.tiamosu.fly.delegate.IFlySupportFragment

/**
 * @author ti
 * @date 2022/7/6.
 */
abstract class FlySupportFragment : Fragment(), IFlySupportFragment {
    private val delegate by lazy { FlySupportFragmentDelegate(this) }
    val startDelegate = ActivityResultDelegate(apply { })

    protected lateinit var activity: AppCompatActivity

    override fun getContext() = activity

    override val bundle: Bundle?
        get() = arguments

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delegate.onViewCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setContentView(inflater, container, savedInstanceState)
    }

    /**
     * 设置布局视图
     */
    override fun setContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return delegate.setContentView(inflater, container)
    }

    /**
     * 相关函数初始化
     */
    override fun initFragment() {
        delegate.initFragment()
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return delegate.onCreateAnimation(enter, nextAnim)
    }

    override fun onSupportVisible() {
    }

    override fun onSupportInvisible() {
    }

    override fun isSupportVisible(): Boolean {
        return delegate.isSupportVisible()
    }

    override fun onResume() {
        super.onResume()
        delegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        delegate.onPause()
    }

    override fun onDestroyView() {
        delegate.onDestroyView()
        super.onDestroyView()
    }

    /**
     * 延迟加载处理回调
     */
    internal fun launchWhenLazyResumedHandle(runnable: Runnable) {
        delegate.launchWhenLazyResumedHandle(runnable)
    }

    /**
     * 页面回退处理
     */
    override fun onBackPressedSupport() = false
}