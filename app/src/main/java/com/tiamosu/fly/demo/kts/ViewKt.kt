package com.tiamosu.fly.demo.kts

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

/**
 * 绑定 Recyclerview
 *
 * @param layoutManger 布局管理器
 * @param bindAdapter 适配器
 * @param isScroll 当滑动控件嵌套 RecyclerView 时，设置为 false，把 RecyclerView 的滑动焦点交给父控件
 * @param hasFixedSize 当我们确定 item 的改变不会影响 RecyclerView 的宽高的时候可以设置 setHasFixedSize(true)，
 * 并通过 Adapter 的增删改插方法去刷新 RecyclerView，而不是通过 notifyDataSetChanged()。
 * （其实可以直接设置为 true，当需要改变宽高的时候就用 notifyDataSetChanged() 去整体刷新一下）
 */
fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager = LinearLayoutManager(context),
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true,
    hasFixedSize: Boolean = true
): RecyclerView {
    layoutManager = layoutManger
    setHasFixedSize(hasFixedSize)
    adapter = bindAdapter
    if (!isScroll) {
        //解决卡顿、滚动粘连
        this.isNestedScrollingEnabled = false
        //如果不设置 false 会引起滚动条初始化的时候滚到 RecyclerView 的位置
        this.isFocusable = false
    }
    return this
}