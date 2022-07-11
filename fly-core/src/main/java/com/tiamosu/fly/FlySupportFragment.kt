package com.tiamosu.fly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tiamosu.fly.action.BundleAction
import com.tiamosu.fly.action.HandlerAction
import com.tiamosu.fly.action.KeyboardAction
import com.tiamosu.fly.action.ScenesAction

/**
 * @author ti
 * @date 2022/7/6.
 */
abstract class FlySupportFragment : Fragment(),
    ScenesAction, BundleAction, KeyboardAction, HandlerAction {
    private val fragmentActivity by lazy { requireActivity() }

    private var isAnimationEnd = false      //转场动画加载完毕
    private var isCreateAnimation = false   //是否有执行转场动画

    override fun getContext() = fragmentActivity

    override val bundle: Bundle?
        get() = arguments

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
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
    protected open fun setContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when {
            getLayoutId() > 0 -> inflater.inflate(getLayoutId(), container, false)
            else -> null
        }
    }

    /**
     * 相关函数初始化
     */
    protected open fun initFragment() {
        initParameter(bundle)
        initView()
        initEvent()
        initObserver()
        loadData()

        lifecycleScope.launchWhenResumed { startLazyLoadData() }
    }

    //延迟加载，防止动画还未执行完毕紧接着加载数据，导致页面渲染卡顿
    private fun startLazyLoadData() {
        if (!isResumed || (isCreateAnimation && !isAnimationEnd)) return
        lazyLoadData()
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        isCreateAnimation = true

        fun onAnimationEnd() {
            isAnimationEnd = true
            startLazyLoadData()
        }
        if (nextAnim <= 0) {
            onAnimationEnd()
            return super.onCreateAnimation(transit, enter, nextAnim)
        }
        return AnimationUtils.loadAnimation(context, nextAnim).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    if (enter) {
                        onAnimationEnd()
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        isAnimationEnd = false
        isCreateAnimation = false
        removeCallbacks()
        super.onDestroyView()
    }
}