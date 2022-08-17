package com.tiamosu.fly.kts

import androidx.lifecycle.ViewModel
import com.tiamosu.fly.scope.ApplicationInstance

/**
 * @author tiamosu
 * @date 2022/7/8
 */

/**
 * 获取 Application 级别的 ViewModel
 */
inline fun <reified VM : ViewModel> appViewModel(): Lazy<VM> {
    return lazy {
        ApplicationInstance.appViewModelProvider[VM::class.java]
    }
}