package com.tiamosu.fly.utils

import android.os.Handler
import android.os.Looper

/**
 * @author ti
 * @date 2022/9/13.
 */
object ViewUtils {

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    /**
     * 在UI主线程运行
     */
    fun runOnUiThread(runnable: (() -> Unit)? = null) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable?.invoke()
        } else {
            handler.post { runnable?.invoke() }
        }
    }
}