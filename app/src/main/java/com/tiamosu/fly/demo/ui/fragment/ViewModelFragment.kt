package com.tiamosu.fly.demo.ui.fragment

import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.blankj.utilcode.util.ToastUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentViewModelBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.bridge.ExampleViewModel
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/13.
 */
class ViewModelFragment : BaseFragment(), MavericksView {
    private val binding by viewBinding<FragmentViewModelBinding>()
    private val viewModel: ExampleViewModel by fragmentViewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_view_model
    }

    override fun initEvent() {
        binding?.viewModelBtnShare?.clickNoRepeat {
            val state = sharedViewModel.updateState.value ?: false
            sharedViewModel.updateState.value = !state
        }

        binding?.viewModelBtnCount?.clickNoRepeat {
            viewModel.incrementCount()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        ToastUtils.showLong("Count:${state.count}")
    }
}