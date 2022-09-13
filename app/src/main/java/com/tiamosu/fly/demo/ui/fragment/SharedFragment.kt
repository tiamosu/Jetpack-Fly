package com.tiamosu.fly.demo.ui.fragment

import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentSharedBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.domain.event.Messages
import com.tiamosu.fly.demo.pageMessenger
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/13.
 */
class SharedFragment : BaseFragment() {
    private val binding by viewBinding<FragmentSharedBinding>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_shared
    }

    override fun initEvent() {
        binding?.sharedBtnShare?.clickNoRepeat(interval = 0) {
            pageMessenger.input(Messages.UpdateState.apply {
                isTurnOn = !isTurnOn
            })
        }
    }
}