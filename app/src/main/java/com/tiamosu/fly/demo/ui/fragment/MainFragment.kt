package com.tiamosu.fly.demo.ui.fragment

import androidx.fragment.app.Fragment
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
            add(SearchFragment())
            add(ViewFragment())
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        binding?.mainVp2?.init(
            fragment = this,
            fragments = fragments,
            offscreenPageLimit = 1
        )
    }

    override fun initObserver() {
        sharedViewModel.updateState.observe(this) {
            ToastUtils.showLong("状态：${if (it) "开启" else "关闭"}")
        }
    }
}