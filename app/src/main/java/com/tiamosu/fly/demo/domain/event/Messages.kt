package com.tiamosu.fly.demo.domain.event

/**
 * @author ti
 * @date 2022/9/13.
 */
sealed class Messages {

    object UpdateState : Messages() {
        var isTurnOn: Boolean = true
    }
}
