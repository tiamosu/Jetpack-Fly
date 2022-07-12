package com.tiamosu.fly.demo.ui.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.ItemHomeBinding
import com.tiamosu.fly.demo.data.HomeEntity
import com.tiamosu.fly.viewbinding.dataBinding

/**
 * @author ti
 * @date 2022/7/11.
 */
class HomeAdapter : BaseQuickAdapter<HomeEntity, HomeViewHolder>(R.layout.item_home) {

    override fun convert(holder: HomeViewHolder, item: HomeEntity) {
        holder.binding?.homeTvText?.text = item.title
    }
}

class HomeViewHolder(view: View) : BaseViewHolder(view) {
    val binding by dataBinding<ItemHomeBinding>()
}