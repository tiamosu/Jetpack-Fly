package com.tiamosu.fly.demo

import com.airbnb.mvrx.Mavericks
import com.tiamosu.fly.FlySupportApplication

/**
 * @author ti
 * @date 2022/7/18.
 */
class MyApp : FlySupportApplication() {

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}