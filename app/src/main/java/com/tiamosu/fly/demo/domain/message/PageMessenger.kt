package com.tiamosu.fly.demo.domain.message

import com.kunminx.architecture.domain.dispatch.MviDispatcher
import com.tiamosu.fly.demo.domain.event.Messages

/**
 * @author ti
 * @date 2022/9/13.
 */
class PageMessenger : MviDispatcher<Messages>() {

    override fun onHandle(intent: Messages?) {
        intent?.let { sendResult(it) }
    }
}