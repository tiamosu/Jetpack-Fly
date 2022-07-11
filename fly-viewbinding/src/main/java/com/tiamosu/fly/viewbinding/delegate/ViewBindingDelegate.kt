package com.tiamosu.fly.viewbinding.delegate

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.tiamosu.fly.viewbinding.kts.launchWhenDestroyed
import kotlin.properties.ReadOnlyProperty

/**
 * @author ti
 * @date 2022/7/11.
 */
abstract class ViewBindingDelegate<T, V : ViewBinding?>(t: T) : ReadOnlyProperty<T, V?> {
    protected val tag: String by lazy { this::class.java.simpleName }
    private val clearHandle by lazy { Handler(Looper.getMainLooper()) }

    protected var binding: V? = null
        set(value) {
            if (value != null && value != field) {
                create(value)
            }
            field = value
        }

    protected open fun create(v: V?) {}

    protected open fun clear(v: V?) {}

    init {
        val lifecycleOwner = when (t) {
            is Fragment -> t
            is ComponentActivity -> t
            is View -> t.context as? ComponentActivity
            else -> null
        }
        lifecycleOwner?.launchWhenDestroyed {
            destroy()
        }
    }

    private fun destroy() {
        clearHandle.post {
            clear(binding)
            binding = null
        }
    }
}