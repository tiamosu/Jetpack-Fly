package com.tiamosu.fly.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment

/**
 * @author ti
 * @date 2022/7/12.
 */
class FlySupportFragmentDelegate(private val iFlySupport: IFlySupportFragment) {
    private val fragment by lazy { checkNotNull(iFlySupport as? Fragment) }
    private val visibleDelegate by lazy { FlyVisibleDelegate(iFlySupport, this) }

    private var isAnimationEnd = false      //转场动画加载完毕
    private var isCreateAnimation = false   //是否有执行转场动画
    private var isLazyLoaded = false        //是否执行完懒加载函数

    fun onViewCreated() {
        iFlySupport.initFragment()
    }

    fun setContentView(inflater: LayoutInflater, container: ViewGroup?): View? {
        val layoutId = iFlySupport.getLayoutId()
        return when {
            layoutId > 0 -> inflater.inflate(layoutId, container, false)
            else -> null
        }
    }

    fun initFragment() {
        iFlySupport.apply {
            initParameter(bundle)
            initView()
            initEvent()
            initObserver()
            loadData()
        }
    }

    fun onCreateAnimation(enter: Boolean, nextAnim: Int): Animation? {
        isCreateAnimation = true

        fun onAnimationEnd() {
            isAnimationEnd = true
            startLazyLoadData()
        }
        if (nextAnim <= 0) {
            onAnimationEnd()
            return null
        }
        return AnimationUtils.loadAnimation(fragment.context, nextAnim).apply {
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

    fun onResume() {
        visibleDelegate.onResume()
    }

    fun onPause() {
        visibleDelegate.onPause()
    }

    fun onDestroyView() {
        isAnimationEnd = false
        isCreateAnimation = false
        isLazyLoaded = false
        iFlySupport.removeCallbacks()
        visibleDelegate.onDestroyView()
    }

    fun isSupportVisible() = visibleDelegate.isSupportVisible()

    //延迟加载，防止动画还未执行完毕紧接着加载数据，导致页面渲染卡顿
    internal fun startLazyLoadData() {
        if (!isSupportVisible() || (isCreateAnimation && !isAnimationEnd) || isLazyLoaded) return
        isLazyLoaded = true
        iFlySupport.onLazyLoad()
    }
}