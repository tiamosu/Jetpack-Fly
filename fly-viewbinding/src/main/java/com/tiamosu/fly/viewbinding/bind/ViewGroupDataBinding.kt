package com.tiamosu.fly.viewbinding.bind

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

/**
 * @author tiamosu
 * @date 2022/7/10
 */
class ViewGroupDataBinding<V : ViewDataBinding>(
    bindingClass: Class<V>,
    viewGroup: ViewGroup,
    attachToRoot: Boolean = false
) : ViewGroupViewBinding<V>(bindingClass, viewGroup, attachToRoot)
