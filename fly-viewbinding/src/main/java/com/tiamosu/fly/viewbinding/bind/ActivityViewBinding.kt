package com.tiamosu.fly.viewbinding.bind

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.tiamosu.fly.viewbinding.ViewBindUtils
import com.tiamosu.fly.viewbinding.delegate.ViewBindingDelegate
import com.tiamosu.fly.viewbinding.kts.bindMethod
import kotlin.reflect.KProperty

/**
 * @author ti
 * @date 2022/7/8.
 */
@Suppress("DEPRECATION")
open class ActivityViewBinding<V : ViewBinding>(
    bindingClass: Class<V>,
    private val activity: ComponentActivity
) : ViewBindingDelegate<ComponentActivity, V>(activity) {
    private val bindMethod by lazy { bindingClass.bindMethod() }
    private val rootView by lazy { ViewBindUtils.findRootView(activity) }

    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): V? {
        if (binding != null && binding?.root !== rootView) {
            binding = null
        }
        binding?.let { return it }

        val lifecycle = activity.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            Log.e(tag, "Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
            return null
        }

        @Suppress("UNCHECKED_CAST")
        kotlin.runCatching {
            binding = bindMethod.invoke(null, rootView) as? V
        }.onFailure {
            it.printStackTrace()
        }
        return binding
    }
}