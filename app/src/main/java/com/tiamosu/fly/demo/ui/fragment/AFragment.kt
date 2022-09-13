package com.tiamosu.fly.demo.ui.fragment

import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentABinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.navigation.navigator
import com.tiamosu.fly.navigation.pop
import com.tiamosu.fly.navigation.startWithPopTo
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/12.
 */
class AFragment : BaseFragment() {
    private val binding by viewBinding<FragmentABinding>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_a
    }

    override fun initEvent() {
        binding?.aBtnBack?.clickNoRepeat {
            navigator.pop()
        }

        binding?.aBtnStart?.clickNoRepeat {
//            navigator.start(R.id.bFragment)
            navigator.startWithPopTo(R.id.bFragment, popUpToId = R.id.aFragment, inclusive = true)
        }
    }
}