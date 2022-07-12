package com.tiamosu.fly.demo.ui.fragment

import android.util.Log
import androidx.core.view.updateLayoutParams
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.IntentUtils
import com.blankj.utilcode.util.ToastUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentHomeBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.data.HomeEntity
import com.tiamosu.fly.demo.kts.init
import com.tiamosu.fly.demo.ui.adapter.HomeAdapter
import com.tiamosu.fly.kts.startForActivityResult
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
                0 -> startForActivityResult(IntentUtils.getLaunchAppDetailsSettingsIntent(AppUtils.getAppPackageName())) {
                    Log.e("susu", "result:$it")
                    ToastUtils.showLong(it.toString())
                }
                1 -> navigator.start(R.id.aFragment)
            }
        }
    }

    override fun onLazyLoad() {
        mutableListOf<HomeEntity>().apply {
            add(HomeEntity(0, "ActivityResult"))
            add(HomeEntity(1, "Navigation"))
        }.let(adapter::setList)
    }
}