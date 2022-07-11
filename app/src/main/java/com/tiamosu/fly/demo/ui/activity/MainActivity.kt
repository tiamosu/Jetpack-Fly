package com.tiamosu.fly.demo.ui.activity

import android.util.Log
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.ActivityMainBinding
import com.tiamosu.fly.demo.base.BaseActivity
import com.tiamosu.fly.viewbinding.dataBinding

class MainActivity : BaseActivity() {
    private val binding by dataBinding<ActivityMainBinding>()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        Log.e("susu", "binding:$binding")
    }
}