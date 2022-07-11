package com.tiamosu.fly.demo.ui.fragment

import com.blankj.utilcode.util.ToastUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentHomeBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/8.
 */
class HomeFragment : BaseFragment() {
    private val binding by viewBinding<FragmentHomeBinding>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initEvent() {
        binding?.homeViewCustom?.setOnClickListener {
            val state = sharedViewModel.updateState.value ?: false
            sharedViewModel.updateState.value = !state
        }
    }

    override fun initObserver() {
        sharedViewModel.updateState.observe(this) {
            ToastUtils.showLong("状态：${if (it) "开启" else "关闭"}")
        }
    }
}