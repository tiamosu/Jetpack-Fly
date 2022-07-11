package com.tiamosu.fly.demo.bridge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author tiamosu
 * @date 2022/7/8
 */
class SharedViewModel : ViewModel() {
    val updateState by lazy { MutableLiveData<Boolean>() }
}