package com.tiamosu.fly.demo.ui.fragment

import androidx.fragment.app.viewModels
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
class ViewModelFragment : BaseFragment() {
    private val binding by viewBinding<FragmentViewModelBinding>()
    private val viewModel by viewModels<ExampleViewModel>()

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

    override fun initObserver() {
        viewModel.count.observe(this) { count ->
            ToastUtils.showLong("count:$count")
        }
    }
}