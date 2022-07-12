package com.tiamosu.fly.kts

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.core.app.ActivityOptionsCompat
import com.tiamosu.fly.FlySupportActivity
import com.tiamosu.fly.FlySupportFragment

/**
 * @author ti
 * @date 2022/7/7.
 */

inline fun <reified T> FlySupportActivity.startForActivityResult(
    noinline block: Intent.() -> Unit = {},
    options: ActivityOptionsCompat? = null,
    noinline callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(T::class.java, block, options, callback)
}

fun FlySupportActivity.startForActivityResult(
    intent: Intent,
    options: ActivityOptionsCompat? = null,
    callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(intent, options, callback)
}

inline fun <reified T> FlySupportFragment.startForActivityResult(
    noinline block: Intent.() -> Unit = {},
    options: ActivityOptionsCompat? = null,
    noinline callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(T::class.java, block, options, callback)
}

fun FlySupportFragment.startForActivityResult(
    intent: Intent,
    options: ActivityOptionsCompat? = null,
    callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(intent, options, callback)
}