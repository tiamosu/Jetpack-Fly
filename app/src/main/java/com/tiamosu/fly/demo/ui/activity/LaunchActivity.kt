package com.tiamosu.fly.demo.ui.activity

import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.ActivityUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.ActivityLaunchBinding
import com.tiamosu.fly.demo.base.BaseActivity
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.viewbinding.dataBinding

/**
 * @author ti
 * @date 2022/7/6.
 */
class LaunchActivity : BaseActivity() {
    private val binding by dataBinding<ActivityLaunchBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun initCreate() {
        super.initCreate()
        Log.e("susu", "initCreate")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_launch
    }

    override fun initEvent() {
        binding?.launchBtnStart?.clickNoRepeat {
            ActivityUtils.startActivity(MainActivity::class.java)
            ActivityUtils.finishActivity(this)
        }
    }
}