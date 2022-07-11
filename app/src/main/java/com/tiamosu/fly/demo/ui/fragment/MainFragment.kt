package com.tiamosu.fly.demo.ui.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
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

    override fun initEvent() {
        binding?.mainVp2?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val id = when (position) {
                    0 -> R.id.main_home
                    1 -> R.id.main_search
                    else -> R.id.main_view
                }
                binding?.mainNavigation?.selectedItemId = id
            }
        })

        binding?.mainNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main_home -> switchFragment(0)
                R.id.main_search -> switchFragment(1)
                R.id.main_view -> switchFragment(2)
            }
            true
        }
    }

    override fun initObserver() {
        sharedViewModel.updateState.observe(this) {
            ToastUtils.showLong("状态：${if (it) "开启" else "关闭"}")
        }
    }

    /**
     * 切换页面
     */
    private fun switchFragment(index: Int) {
        binding?.mainVp2?.currentItem = index
    }
}