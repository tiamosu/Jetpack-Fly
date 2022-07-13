package com.tiamosu.fly.demo.bridge

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @author ti
 * @date 2022/7/13.
 */
class ExampleViewModel : ViewModel() {
    val liveData by lazy { UnPeekLiveData<Int>() }
    private var count = 0

    fun count() {
        count++
        liveData.value = count
    }
}