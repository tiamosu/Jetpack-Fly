package com.tiamosu.fly.navigation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import com.blankj.utilcode.util.ActivityUtils

val Fragment.navController: NavController
    get() = NavHostFragment.findNavController(this)

fun ComponentActivity.navController(@IdRes viewId: Int): NavController {
    return findNavController(viewId)
}

val View.navController: NavController
    get() = findNavController()

/**
 * 获取 navigator 实例，执行 [NavHostKt] 中的相关操作
 */
val Fragment.navigator: NavHostKt
    get() = NavHostKt(requireContext(), object : MyNavHost(navController) {})

/**
 * 获取 navigator 实例，执行 [NavHostKt] 中的相关操作
 *
 * @param viewId 控件id
 */
fun ComponentActivity.navigator(@IdRes viewId: Int): NavHostKt {
    return NavHostKt(this, object : MyNavHost(navController(viewId)) {})
}

/**
 * 获取 navigator 实例，执行 [NavHostKt] 中的相关操作
 */
val View.navigator: NavHostKt
    get() = NavHostKt(context, object : MyNavHost(navController) {})


/* ====================== 启动 Fragment start======================*/

/**
 * 启动新的 Fragment
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param args 传递给目的地的参数
 * @param interval 设置防抖间隔时间，单位毫秒
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param builder [NavOptionsBuilder] 配置
 */
fun NavHostKt.start(
    @IdRes resId: Int,
    args: Bundle? = null,
    interval: Long = 500,
    navigatorExtras: Navigator.Extras? = null,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    startNav(
        resId = resId,
        args = args,
        interval = interval,
        builder = builder,
        navigatorExtras = navigatorExtras
    )
}

/**
 * 启动新的 Fragment，并关闭 [popUpToId] 之上的 Fragments
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param popUpToId 退出栈到指定目标ID
 * @param inclusive 指定目标[popUpToId]是否也弹出
 * @param args 传递给目的地的参数
 * @param interval 设置防抖间隔时间，单位毫秒
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param builder [NavOptionsBuilder] 配置
 */
fun NavHostKt.startWithPopTo(
    @IdRes resId: Int,
    popUpToId: Int,
    inclusive: Boolean = false,
    args: Bundle? = null,
    interval: Long = 500,
    navigatorExtras: Navigator.Extras? = null,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    startNav(
        resId = resId,
        popUpToId = popUpToId,
        inclusive = inclusive,
        args = args,
        interval = interval,
        builder = builder,
        navigatorExtras = navigatorExtras
    )
}
/* ====================== 启动 Fragment end======================*/


/* ====================== 出栈 start======================*/

/**
 * 出栈当前 Fragment
 */
fun NavHostKt.pop() {
    navigateUp()
}

/**
 * 出栈 [popUpToId] 之上的 Fragments
 *
 * @param popUpToId 退出栈到指定目标ID
 * @param inclusive 指定目标[popUpToId]是否也弹出
 */
fun NavHostKt.popTo(
    @IdRes popUpToId: Int,
    inclusive: Boolean = false
) {
    popBackStack(popUpToId, inclusive)
}
/* ====================== 出栈 end======================*/


/**
 * Fragment页面跳转
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param popUpToId 退出栈到指定目标ID
 * @param inclusive 指定目标[popUpToId]是否也弹出
 * @param singleTop 是否进行栈顶复用
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param args 传递给目的地的参数
 * @param interval 设置防抖间隔时间，单位毫秒
 * @param builder [NavOptionsBuilder] 配置
 */
private fun NavHostKt.startNav(
    @IdRes resId: Int = -1,
    @IdRes popUpToId: Int = -1,
    inclusive: Boolean = false,
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
                anim { applyAnimSlide() }
                popUpTo(popUpToId) { this.inclusive = inclusive }
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