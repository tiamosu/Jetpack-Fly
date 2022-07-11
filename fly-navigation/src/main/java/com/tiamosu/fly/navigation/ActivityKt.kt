package com.tiamosu.fly.navigation

import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.*
import com.tiamosu.fly.kts.isValid

/**
 * @author tiamosu
 * @date 2022/7/8
 */

/**
 * 获取导航控制器[navController]
 *
 * @param viewId 视图控件id，必传
 */
fun AppCompatActivity.navController(@IdRes viewId: Int): NavController {
    return findNavController(viewId)
}

/**
 * 尝试在导航层次结构中向上导航（页面返回到上一页）
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 */
fun AppCompatActivity.navigateUp(@IdRes viewId: Int): Boolean {
    return kotlin.runCatching {
        navController(viewId).navigateUp()
    }.onFailure {
        it.printStackTrace()
    }.isSuccess
}

/**
 * 尝试将控制器的后退栈弹出到特定的目的地（页面返回到给定的目的地）
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 * @param destinationId 所要达到的目的地id
 * @param inclusive 给定的目的地是否也弹出
 */
fun AppCompatActivity.popBackStack(
    @IdRes viewId: Int,
    @IdRes destinationId: Int? = null,
    inclusive: Boolean = false
): Boolean {
    return kotlin.runCatching {
        when {
            destinationId != null -> navController(viewId).popBackStack(destinationId, inclusive)
            else -> navController(viewId).popBackStack()
        }
    }.onFailure {
        it.printStackTrace()
    }.isSuccess
}

/**
 * 从当前导航图导航到目标
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 * @param resId 操作ID或要导航到的目标ID
 * @param args 传递给目的地的参数
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun AppCompatActivity.navigate(
    @IdRes viewId: Int,
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval)) return
    kotlin.runCatching {
        navController(viewId).navigate(resId, args, navOptions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的深层链接导航到目的地
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 * @param deepLink 从当前导航图[NavGraph]可到达目标的deepLink
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun AppCompatActivity.navigate(
    @IdRes viewId: Int,
    deepLink: Uri,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval)) return
    kotlin.runCatching {
        navController(viewId).navigate(deepLink, navOptions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的[NavDirections]导航到目的地
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 * @param directions 描述此导航操作的说明
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun AppCompatActivity.navigate(
    @IdRes viewId: Int,
    directions: NavDirections,
    navOptions: NavOptions? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval)) return
    kotlin.runCatching {
        navController(viewId).navigate(directions, navOptions)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的[NavDirections]导航到目的地
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 * @param directions 描述此导航操作的说明
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun AppCompatActivity.navigate(
    @IdRes viewId: Int,
    directions: NavDirections,
    navigatorExtras: Navigator.Extras,
    interval: Long = 500
) {
    if (!isValid(interval = interval)) return
    kotlin.runCatching {
        navController(viewId).navigate(directions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的[NavDeepLinkRequest]导航到目的地
 *
 * @param viewId 可通过viewId获取导航控制器[navController]
 * @param request 从当前导航图[NavGraph]可到达目标的deepLinkRequest
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun AppCompatActivity.navigate(
    @IdRes viewId: Int,
    request: NavDeepLinkRequest,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval)) return
    kotlin.runCatching {
        navController(viewId).navigate(request, navOptions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}