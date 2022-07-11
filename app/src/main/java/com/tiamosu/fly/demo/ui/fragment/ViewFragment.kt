package com.tiamosu.fly.demo.ui.fragment

import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentViewBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author tiamosu
 * @date 2022/7/9
 */
class ViewFragment : BaseFragment() {
    private val binding by viewBinding<FragmentViewBinding>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_view
    }

    override fun initEvent() {
        binding?.viewCustomView?.clickNoRepeat {
            val state = sharedViewModel.updateState.value ?: false
            sharedViewModel.updateState.value = !state
        }
    }
}