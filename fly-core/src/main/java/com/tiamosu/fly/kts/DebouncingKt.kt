package com.tiamosu.fly.kts

import com.blankj.utilcode.util.DebouncingUtils

/**
 * @author tiamosu
 * @date 2022/7/8
 */

/**
 * 防抖
 *
 * @param key 键值
 * @param interval  间隔时间
 */
fun Any.isValid(
    key: String = this.hashCode().toString(),
    interval: Long = 500
): Boolean {
    return DebouncingUtils.isValid(key, interval)
}