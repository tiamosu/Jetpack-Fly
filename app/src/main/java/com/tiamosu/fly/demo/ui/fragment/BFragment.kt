package com.tiamosu.fly.demo.ui.fragment

import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentBBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.navigation.navigator
import com.tiamosu.fly.navigation.pop
import com.tiamosu.fly.navigation.start
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/12.
 */
class BFragment : BaseFragment() {
    private val binding by viewBinding<FragmentBBinding>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_b
    }

    override fun initEvent() {
        binding?.bBtnBack?.clickNoRepeat {
            navigator.pop()
        }

        binding?.bBtnStart?.clickNoRepeat {
            navigator.start(R.id.bFragment, singleTop = true)
        }
    }
}