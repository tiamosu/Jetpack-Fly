package com.tiamosu.fly.demo.bridge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author ti
 * @date 2022/7/13.
 */
class ExampleViewModel : ViewModel() {
    val count by lazy { MutableLiveData<Int>() }

    fun incrementCount() {
        count.value = (count.value ?: 0) + 1
    }
}