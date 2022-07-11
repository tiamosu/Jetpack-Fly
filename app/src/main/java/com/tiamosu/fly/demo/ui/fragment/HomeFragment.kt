package com.tiamosu.fly.demo.ui.fragment

import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentHomeBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.kts.init
import com.tiamosu.fly.demo.ui.adapter.HomeAdapter
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/8.
 */
class HomeFragment : BaseFragment() {
    private val binding by viewBinding<FragmentHomeBinding>()
    private val adapter by lazy { HomeAdapter() }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        binding?.homeRv?.init(bindAdapter = adapter)
    }

    override fun lazyLoadData() {
        mutableListOf<String>().apply {
            add("你好啊")
            add("你好啊")
            add("你好啊")
            add("你好啊")
            add("你好啊")
            add("你好啊")
            add("你好啊")
            add("你好啊")
        }.let(adapter::setList)
    }
}