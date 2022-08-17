package com.tiamosu.fly.demo.base

import android.os.Bundle
import com.tiamosu.fly.FlySupportActivity
import com.tiamosu.fly.demo.kts.immersionBar

/**
 * @author ti
 * @date 2022/7/7.
 */
abstract class BaseActivity : FlySupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar { statusBarDarkFont(true) }
    }

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
    }
}