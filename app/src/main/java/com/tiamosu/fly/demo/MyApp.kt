package com.tiamosu.fly.demo

import com.tiamosu.fly.FlySupportApplication
import com.tiamosu.fly.demo.domain.message.PageMessenger
import com.tiamosu.fly.kts.appViewModel

/**
 * @author ti
 * @date 2022/7/18.
 */

val pageMessenger by appViewModel<PageMessenger>()

class MyApp : FlySupportApplication()