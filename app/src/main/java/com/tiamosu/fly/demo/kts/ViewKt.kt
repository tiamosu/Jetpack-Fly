package com.tiamosu.fly.demo.kts

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * @author ti
 * @date 2022/7/8.
 */

/**
 * ViewPager2 初始化
 */
fun ViewPager2.init(
    fragment: Fragment,
    fragments: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = true,
    offscreenPageLimit: Int = fragments.size
) {
    //是否可滑动
    this.isUserInputEnabled = isUserInputEnabled
    //设置页面预加载数量
    this.offscreenPageLimit = offscreenPageLimit
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
}