package com.tiamosu.fly.demo.domain.vm

import com.kunminx.architecture.domain.dispatch.MviDispatcher
import com.tiamosu.fly.demo.domain.event.MviIntent

/**
 * @author ti
 * @date 2022/7/13.
 */
class MviRequester : MviDispatcher<MviIntent>() {

    override fun onHandle(intent: MviIntent?) {
        intent?.let { sendResult(it) }
    }

    fun incrementCount() {
        input(MviIntent.IncrementCount.apply { count++ })
    }
}