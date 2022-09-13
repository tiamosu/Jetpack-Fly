package com.tiamosu.fly.utils

import android.os.SystemClock
import java.util.concurrent.ConcurrentHashMap

/**
 * @author ti
 * @date 2022/9/13.
 */
object DebouncingUtils {
    private const val CACHE_SIZE = 64
    private val KEY_MILLIS_MAP = ConcurrentHashMap<String, Long>(CACHE_SIZE)

    fun isValid(key: String, duration: Long): Boolean {
        require(key.isNotBlank()) { "The key is null." }
        require(duration >= 0) { "The duration is less than 0." }
        val curTime = SystemClock.elapsedRealtime()
        clearIfNecessary(curTime)
        val validTime = KEY_MILLIS_MAP[key]
        if (validTime == null || curTime >= validTime) {
            KEY_MILLIS_MAP[key] = curTime + duration
            return true
        }
        return false
    }

    private fun clearIfNecessary(curTime: Long) {
        if (KEY_MILLIS_MAP.size < CACHE_SIZE) return
        val it = KEY_MILLIS_MAP.entries.iterator()
        while (it.hasNext()) {
            val (_, validTime) = it.next()
            if (curTime >= validTime) {
                it.remove()
            }
        }
    }
}