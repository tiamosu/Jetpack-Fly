package com.tiamosu.fly.viewbinding.bind

import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import com.tiamosu.fly.kts.lifecycleOwnerEx

/**
 * @author ti
 * @date 2022/7/8.
 */
class ActivityDataBinding<V : ViewDataBinding>(
    bindingClass: Class<V>,
    private val activity: ComponentActivity
) : ActivityViewBinding<V>(bindingClass, activity) {

    override fun create(v: V?) {
        (v as? ViewDataBinding)?.lifecycleOwner = activity.lifecycleOwnerEx
    }

    override fun clear(v: V?) {
        (v as? ViewDataBinding)?.unbind()
    }
}