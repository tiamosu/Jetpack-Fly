package com.tiamosu.fly.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.tiamosu.fly.FlySupportActivity
import com.tiamosu.fly.FlySupportFragment

/**
 * Fragment页面跳转
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param popUpToId 退出栈到指定目标ID
 * @param popUpToInclusive 给定的目的地是否也弹出
 * @param singleTop 是否进行栈顶复用
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 * @param args 传递给目的地的参数
 */
fun FlySupportFragment.startFragment(
    @IdRes resId: Int = -1,
    @IdRes popUpToId: Int = -1,
    popUpToInclusive: Boolean = false,
    singleTop: Boolean = false,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500,
    args: Bundle? = null,
    block: NavOptions.Builder.() -> Unit = {},
) {

    fun start() {
        if (resId == -1) {
            if (popUpToId != -1) {
                popBackStack(popUpToId, popUpToInclusive)
            }
            return
        }
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.nav_slide_in_right)
            .setExitAnim(R.anim.nav_slide_out_left)
            .setPopEnterAnim(R.anim.nav_slide_in_left)
            .setPopExitAnim(R.anim.nav_slide_out_right)
            .apply {
                if (popUpToId != -1) {
                    setPopUpTo(popUpToId, popUpToInclusive)
                }
            }
            .setLaunchSingleTop(singleTop)
            .apply(block)
            .build()

        navigate(
            resId = resId,
            args = args,
            navOptions = navOptions,
            navigatorExtras = navigatorExtras,
            interval = interval
        )
    }

    if (!isStateSaved) {
        start()
    } else {
        context.lifecycleScope.launchWhenResumed { start() }
    }
}

/**
 * Fragment页面跳转
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 * @param resId 操作ID或要导航到的目标ID
 * @param popUpToId 退出栈到指定目标ID
 * @param popUpToInclusive 给定的目的地是否也弹出
 * @param singleTop 是否进行栈顶复用
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 * @param args 传递给目的地的参数
 */
fun FlySupportActivity.startFragment(
    @IdRes viewId: Int,
    @IdRes resId: Int = -1,
    @IdRes popUpToId: Int = -1,
    popUpToInclusive: Boolean = false,
    singleTop: Boolean = false,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500,
    args: Bundle? = null,
    block: NavOptions.Builder.() -> Unit = {},
) {

    fun start() {
        if (resId == -1) {
            if (popUpToId != -1) {
                popBackStack(viewId, popUpToId, popUpToInclusive)
            }
            return
        }
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.nav_slide_in_right)
            .setExitAnim(R.anim.nav_slide_out_left)
            .setPopEnterAnim(R.anim.nav_slide_in_left)
            .setPopExitAnim(R.anim.nav_slide_out_right)
            .apply {
                if (popUpToId != -1) {
                    setPopUpTo(popUpToId, popUpToInclusive)
                }
            }
            .setLaunchSingleTop(singleTop)
            .apply(block)
            .build()

        navigate(
            viewId = viewId,
            resId = resId,
            args = args,
            navOptions = navOptions,
            navigatorExtras = navigatorExtras,
            interval = interval
        )
    }

    lifecycleScope.launchWhenResumed { start() }
}