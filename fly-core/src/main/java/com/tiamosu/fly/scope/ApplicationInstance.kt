package com.tiamosu.fly.scope

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @author ti
 * @date 2022/8/17.
 */
object ApplicationInstance : ViewModelStoreOwner {
    private val appViewModelStore by lazy { ViewModelStore() }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

    val appViewModelProvider: ViewModelProvider
        get() = ViewModelProvider(this)
}