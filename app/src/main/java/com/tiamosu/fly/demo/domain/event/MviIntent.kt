package com.tiamosu.fly.demo.domain.event

/**
 * @author ti
 * @date 2022/9/13.
 */
sealed class MviIntent {

    object IncrementCount : MviIntent() {
        var count = 0
    }
}