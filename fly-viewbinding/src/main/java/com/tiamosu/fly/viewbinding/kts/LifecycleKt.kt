package com.tiamosu.fly.viewbinding.kts

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.tiamosu.fly.kts.lifecycleOwnerEx
import kotlinx.coroutines.launch

/**
 * @author ti
 * @date 2022/7/11.
 */

fun LifecycleOwner.launchWhenDestroyed(destroyed: (() -> Unit)? = null) {
    fun getViewLifecycleOwner(block: (LifecycleOwner) -> Unit) {
        when (this) {
            is Fragment -> {
                this.viewLifecycleOwnerLiveData.observe(this) {
                    block.invoke(it.lifecycleOwnerEx)
                }
            }
            else -> {
                block.invoke(this)
            }
        }
    }
    lifecycleScope.launch {
        getViewLifecycleOwner { lifecycleOwner ->
            lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    owner.lifecycle.removeObserver(this)
                    destroyed?.invoke()
                }
            })
        }
    }
}