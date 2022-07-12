package com.tiamosu.fly.navigation

import androidx.navigation.AnimBuilder

/**
 * @author ti
 * @date 2022/7/12.
 */

//左右入场出场的动画
fun AnimBuilder.applyAnimSlide() {
    enter = R.anim.nav_slide_in_right
    exit = R.anim.nav_slide_out_left
    popEnter = R.anim.nav_slide_in_left
    popExit = R.anim.nav_slide_out_right
}

//默认的上下透明动画
fun AnimBuilder.applyAnimDefault() {
    enter = R.anim.nav_default_enter
    exit = R.anim.nav_default_exit
    popEnter = R.anim.nav_default_enter
    popExit = R.anim.nav_default_exit
}