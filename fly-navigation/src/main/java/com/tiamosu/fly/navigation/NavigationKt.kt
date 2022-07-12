package com.tiamosu.fly.navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.blankj.utilcode.util.ActivityUtils

/**
 * 获取 navigator 实例，执行 [NavHostKt] 中的相关操作
 */
val Fragment.navigator
    get() = NavHostKt(requireContext(), object : NavHost {
        override val navController: NavController
            get() = findNavController()
    })

/**
 * 获取 navigator 实例，执行 [NavHostKt] 中的相关操作
 *
 * @param viewId 控件id
 */
fun ComponentActivity.navigator(@IdRes viewId: Int): NavHostKt {
    return NavHostKt(this, object : NavHost {
        override val navController: NavController
            get() = findNavController(viewId)
    })
}

/**
 * 获取 navigator 实例，执行 [NavHostKt] 中的相关操作
 */
val View.navigator
    get() = NavHostKt(context, object : NavHost {
        override val navController: NavController
            get() = findNavController()
    })

/**
 *
 */
fun NavHostKt.start(
    @IdRes resId: Int,
    args: Bundle? = null,
    interval: Long = 500,
    navigatorExtras: Navigator.Extras? = null,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(
        resId = resId,
        args = args,
        interval = interval,
        builder = builder,
        navigatorExtras = navigatorExtras
    )
}

/**
 * Fragment页面跳转
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param popUpToId 退出栈到指定目标ID
 * @param popUpToInclusive 给定的目的地是否也弹出
 * @param singleTop 是否进行栈顶复用
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param args 传递给目的地的参数
 * @param interval 设置防抖间隔时间，单位毫秒
 */
private fun NavHostKt.navigate(
    @IdRes resId: Int = -1,
    @IdRes popUpToId: Int = -1,
    popUpToInclusive: Boolean = false,
    singleTop: Boolean = false,
    navigatorExtras: Navigator.Extras? = null,
    args: Bundle? = null,
    interval: Long = 500,
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    context.startNav {
        navigate(
            resId = resId,
            args = args,
            navOptions = navOptions {
                anim { applyAnimDefault() }
                popUpTo(popUpToId) { inclusive = popUpToInclusive }
                launchSingleTop = singleTop
                apply(builder)
            },
            navigatorExtras = navigatorExtras,
            interval = interval
        )
    }
}

private fun Context.startNav(start: () -> Unit) {
    val activity = ActivityUtils.getActivityByContext(this) as? ComponentActivity
    activity?.lifecycleScope?.launchWhenResumed { start.invoke() }
}