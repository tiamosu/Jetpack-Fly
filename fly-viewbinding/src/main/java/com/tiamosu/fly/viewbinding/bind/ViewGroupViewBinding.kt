package com.tiamosu.fly.viewbinding.bind

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.tiamosu.fly.viewbinding.delegate.ViewBindingDelegate
import com.tiamosu.fly.viewbinding.kts.inflateMethod2
import com.tiamosu.fly.viewbinding.kts.inflateMethod3
import com.tiamosu.fly.viewbinding.layoutInflater
import kotlin.reflect.KProperty

/**
 * @author ti
 * @date 2022/7/8.
 */
open class ViewGroupViewBinding<V : ViewBinding>(
    private val bindingClass: Class<V>,
    viewGroup: ViewGroup,
    private val attachToRoot: Boolean = false
) : ViewBindingDelegate<ViewGroup, V?>(viewGroup) {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): V? {
        binding?.let { return it }

        kotlin.runCatching {
            val inflateMethod = bindingClass.inflateMethod3()
            binding = inflateMethod.invoke(null, layoutInflater, thisRef, attachToRoot) as? V
        }
        if (binding == null) {
            kotlin.runCatching {
                val inflateMethod = bindingClass.inflateMethod2()
                binding = inflateMethod.invoke(null, layoutInflater, thisRef) as? V
            }
        }
        return binding
    }
}