package com.tiamosu.fly.delegate

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.tiamosu.fly.kts.context
import com.tiamosu.fly.kts.lifecycleOwnerEx
import java.util.*

/**
 * @author tiamosu
 * @date 2022/7/12
 */
class ActivityResultDelegate(private val lifecycleOwner: LifecycleOwner) :
    DefaultLifecycleObserver {
    private val resultCallbacks: Deque<(ActivityResult) -> Unit> by lazy { ArrayDeque() }

    private val activityForResult = when (lifecycleOwner) {
        is Fragment -> {
            lifecycleOwner.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                resultCallbacks.pop()?.invoke(result)
            }
        }
        is ComponentActivity -> {
            lifecycleOwner.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                resultCallbacks.pop()?.invoke(result)
            }
        }
        else -> null
    }

    init {
        when (lifecycleOwner) {
            is Fragment -> lifecycleOwner.lifecycleOwnerEx
            else -> lifecycleOwner
        }.run {
            lifecycle.addObserver(this@ActivityResultDelegate)
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
        resultCallbacks.clear()
        activityForResult?.unregister()
    }

    fun startForActivityResult(
        cls: Class<*>,
        block: Intent.() -> Unit = {},
        options: ActivityOptionsCompat? = null,
        callback: (ActivityResult) -> Unit
    ) {
        val intent = Intent(lifecycleOwner.context, cls).apply(block)
        startForActivityResult(intent, options, callback)
    }

    fun startForActivityResult(
        intent: Intent,
        options: ActivityOptionsCompat? = null,
        callback: (ActivityResult) -> Unit
    ) {
        resultCallbacks.push(callback)
        activityForResult?.launch(intent, options)
    }
}