package com.tiamosu.fly.demo.bridge

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @author ti
 * @date 2022/7/13.
 */
class ExampleViewModel : ViewModel() {
    val count by lazy { UnPeekLiveData<Int>() }

    init {
        count.value = 0
    }

    fun incrementCount() {
        count.value = (count.value ?: 0) + 1
    }
}