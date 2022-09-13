package com.tiamosu.fly.demo.ui.fragment

import androidx.fragment.app.viewModels
import com.blankj.utilcode.util.ToastUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentMviBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.domain.event.MviIntent
import com.tiamosu.fly.demo.domain.vm.MviRequester
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/13.
 */
class MviFragment : BaseFragment() {
    private val binding by viewBinding<FragmentMviBinding>()
    private val requester by viewModels<MviRequester>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_mvi
    }

    override fun initEvent() {
        binding?.mviBtnCount?.clickNoRepeat(interval = 0) {
            requester.incrementCount()
        }
    }

    override fun initObserver() {
        requester.output(this) { intent ->
            if (intent is MviIntent.IncrementCount) {
                ToastUtils.showLong("Count:${intent.count}")
            }
        }
    }
}