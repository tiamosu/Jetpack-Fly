package com.tiamosu.fly.demo.bridge

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.PersistState

/**
 * @author ti
 * @date 2022/7/13.
 */

data class CounterState(@PersistState val count: Int = 0) : MavericksState

class MavericksViewModel(state: CounterState) : MavericksViewModel<CounterState>(state) {

    fun incrementCount() = setState { copy(count = count.inc()) }
}