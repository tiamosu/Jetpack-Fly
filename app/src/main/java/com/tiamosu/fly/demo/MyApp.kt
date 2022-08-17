package com.tiamosu.fly.demo

import com.airbnb.mvrx.Mavericks
import com.tiamosu.fly.FlySupportApplication
import com.tiamosu.fly.demo.bridge.SharedViewModel
import com.tiamosu.fly.kts.appViewModel

/**
 * @author ti
 * @date 2022/7/18.
 */

val sharedViewModel by appViewModel<SharedViewModel>()

class MyApp : FlySupportApplication() {

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}