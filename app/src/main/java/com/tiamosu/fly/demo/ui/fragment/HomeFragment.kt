package com.tiamosu.fly.demo.ui.fragment

import androidx.core.view.updateLayoutParams
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentHomeBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.data.HomeEntity
import com.tiamosu.fly.demo.kts.init
import com.tiamosu.fly.demo.ui.adapter.HomeAdapter
import com.tiamosu.fly.kts.statusBarHeight
import com.tiamosu.fly.navigation.navigator
import com.tiamosu.fly.navigation.start
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
        binding?.homeViewTitleBar?.updateLayoutParams { height = statusBarHeight }
        binding?.homeRv?.init(bindAdapter = adapter)
    }

    override fun initEvent() {
        adapter.setOnItemClickListener { _, _, position ->
            val entity = adapter.data[position]
            when (entity.type) {
                1 -> navigator.start(R.id.aFragment)
            }
        }
    }

    override fun lazyLoadData() {
        mutableListOf<HomeEntity>().apply {
            add(HomeEntity(0, "你好啊"))
            add(HomeEntity(1, "Navigation"))
        }.let(adapter::setList)
    }
}