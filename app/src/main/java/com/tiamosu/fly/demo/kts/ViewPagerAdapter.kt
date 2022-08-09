package com.tiamosu.fly.demo.kts

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author ti
 * @date 2022/8/9.
 */

typealias HandleFragment = () -> Fragment

open class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList = mutableListOf<HandleFragment>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position].invoke()
    }

    fun add(fragment: HandleFragment): ViewPagerAdapter {
        fragmentList.add(fragment)
        return this
    }

    fun addAll(fragmentList: List<HandleFragment>): ViewPagerAdapter {
        this.fragmentList.addAll(fragmentList)
        return this
    }
}