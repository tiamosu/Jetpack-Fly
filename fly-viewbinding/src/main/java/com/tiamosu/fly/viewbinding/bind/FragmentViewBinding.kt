package com.tiamosu.fly.viewbinding.bind

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.tiamosu.fly.kts.lifecycleOwnerEx
import com.tiamosu.fly.viewbinding.delegate.ViewBindingDelegate
import com.tiamosu.fly.viewbinding.kts.bindMethod
import kotlin.reflect.KProperty

/**
 * @author ti
 * @date 2022/7/8.
 */
@Suppress("DEPRECATION")
open class FragmentViewBinding<V : ViewBinding>(
    bindingClass: Class<V>,
    private val fragment: Fragment
) : ViewBindingDelegate<Fragment, V?>(fragment) {
    private val bindMethod by lazy { bindingClass.bindMethod() }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V? {
        if (binding != null && binding?.root !== thisRef.view) {
            binding = null
        }
        binding?.let { return it }

        val lifecycle = fragment.lifecycleOwnerEx.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            Log.e(tag, "Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
            return null
        }

        @Suppress("UNCHECKED_CAST")
        binding = bindMethod.invoke(null, thisRef.view) as? V
        return binding
    }
}