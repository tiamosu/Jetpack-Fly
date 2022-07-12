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

/**
 * 启动 Activity 为返回结果
 *
 * @param block 额外配置 [Intent]
 * @param options 额外配置 [ActivityOptionsCompat]
 * @param callback 结果回调
 */
inline fun <reified T> FlySupportActivity.startForActivityResult(
    noinline block: Intent.() -> Unit = {},
    options: ActivityOptionsCompat? = null,
    noinline callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(T::class.java, block, options, callback)
}

/**
 * 启动 Activity 为返回结果
 *
 * @param intent 意图
 * @param options 额外配置 [ActivityOptionsCompat]
 * @param callback 结果回调
 */
fun FlySupportActivity.startForActivityResult(
    intent: Intent,
    options: ActivityOptionsCompat? = null,
    callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(intent, options, callback)
}

/**
 * 启动 Activity 为返回结果
 *
 * @param block 额外配置 [Intent]
 * @param options 额外配置 [ActivityOptionsCompat]
 * @param callback 结果回调
 */
inline fun <reified T> FlySupportFragment.startForActivityResult(
    noinline block: Intent.() -> Unit = {},
    options: ActivityOptionsCompat? = null,
    noinline callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(T::class.java, block, options, callback)
}

/**
 * 启动 Activity 为返回结果
 *
 * @param intent 意图
 * @param options 额外配置 [ActivityOptionsCompat]
 * @param callback 结果回调
 */
fun FlySupportFragment.startForActivityResult(
    intent: Intent,
    options: ActivityOptionsCompat? = null,
    callback: (ActivityResult) -> Unit
) {
    startDelegate.startForActivityResult(intent, options, callback)
}