package com.tiamosu.fly.demo.base

import android.os.Bundle
import android.util.Log
import com.tiamosu.fly.FlySupportFragment
import com.tiamosu.fly.demo.kts.immersionBar

/**
 * @author ti
 * @date 2022/7/7.
 */
abstract class BaseFragment : FlySupportFragment() {

    override fun initParameter(bundle: Bundle?) {
    }

    override fun initView() {
    }

    override fun initEvent() {
    }

    override fun initObserver() {
    }

    override fun loadData() {
    }

    override fun onLazyLoad() {
        Log.e("lifecycle", "${this::class.java.simpleName} --- onLazyLoad")
    }

    override fun onPause() {
        Log.e("lifecycle", "${this::class.java.simpleName} --- onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.e("lifecycle", "${this::class.java.simpleName} --- onResume")
        super.onResume()
        immersionBar { statusBarDarkFont(true) }
    }
}