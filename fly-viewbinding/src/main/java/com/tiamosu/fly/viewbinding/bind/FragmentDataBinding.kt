package com.tiamosu.fly.viewbinding.bind

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.tiamosu.fly.kts.lifecycleOwnerEx

/**
 * @author ti
 * @date 2022/7/8.
 */
class FragmentDataBinding<V : ViewDataBinding>(
    bindingClass: Class<V>,
    private val fragment: Fragment
) : FragmentViewBinding<V>(bindingClass, fragment) {

    override fun create(v: V?) {
        (v as? ViewDataBinding)?.lifecycleOwner = fragment.lifecycleOwnerEx
    }

    override fun clear(v: V?) {
        (v as? ViewDataBinding)?.unbind()
    }
}