package com.tiamosu.fly.demo.ui.fragment

import com.blankj.utilcode.util.ToastUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentViewBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/13.
 */
class ViewBindingFragment : BaseFragment() {
    private val binding by viewBinding<FragmentViewBinding>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_view_binding
    }

    override fun initEvent() {
//        binding?.viewCustomView?.clickNoRepeat {
//            ToastUtils.showLong("This is a custom view")
//        }
    }
}