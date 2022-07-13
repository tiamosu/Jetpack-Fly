package com.tiamosu.fly.demo.ui.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentMainBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.kts.init
import com.tiamosu.fly.viewbinding.dataBinding

/**
 * @author ti
 * @date 2022/7/8.
 */
class MainFragment : BaseFragment() {
    private val binding by dataBinding<FragmentMainBinding>()

    private val fragments by lazy {
        arrayListOf<Fragment>().apply {
            add(HomeFragment())
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        binding?.mainVp2?.init(
            fragment = this,
            fragments = fragments,
            isUserInputEnabled = false
        )
    }

    override fun initObserver() {
        sharedViewModel.updateState.observe(this) {
            ToastUtils.showLong("state：${if (it) "on" else "off"}")
        }
    }

    override fun onBackPressedSupport(): Boolean {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            ActivityUtils.finishAllActivities()
        } else {
            TOUCH_TIME = System.currentTimeMillis()
            Toast.makeText(context, "Press again to exit", Toast.LENGTH_LONG).show()
        }
        return true
    }

    companion object {
        // 再点一次退出程序时间设置
        private const val WAIT_TIME = 2000L
        private var TOUCH_TIME: Long = 0
    }
}