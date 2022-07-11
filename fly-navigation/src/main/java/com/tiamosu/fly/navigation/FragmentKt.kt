package com.tiamosu.fly.navigation

import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.tiamosu.fly.kts.isFragmentAlive
import com.tiamosu.fly.kts.isValid

/**
 * @author tiamosu
 * @date 2022/7/8
 */

/**
 * 获取导航控制器[Fragment.navController]
 */
fun Fragment.navController(): NavController {
    return findNavController()
}

/**
 * 尝试在导航层次结构中向上导航（页面返回到上一页）
 */
fun Fragment.navigateUp(): Boolean {
    if (!isFragmentAlive) return false
    return kotlin.runCatching {
        navController().navigateUp()
    }.onFailure {
        it.printStackTrace()
    }.isSuccess
}

/**
 * 尝试将控制器的后退栈弹出到特定的目的地（页面返回到给定的目的地）
 *
 * @param destinationId 所要达到的目的地id
 * @param inclusive 给定的目的地是否也弹出
 */
fun Fragment.popBackStack(
    @IdRes destinationId: Int? = null,
    inclusive: Boolean = false
): Boolean {
    if (!isFragmentAlive) return false
    return kotlin.runCatching {
        when {
            destinationId != null -> navController().popBackStack(destinationId, inclusive)
            else -> navController().popBackStack()
        }
    }.onFailure {
        it.printStackTrace()
    }.isSuccess
}

/**
 * 从当前导航图导航到目标
 *
 * @param resId 操作ID或要导航到的目标ID
 * @param args 传递给目的地的参数
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun Fragment.navigate(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval) || !isFragmentAlive) return
    kotlin.runCatching {
        navController().navigate(resId, args, navOptions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的深层链接导航到目的地
 *
 * @param deepLink 从当前导航图[NavGraph]可到达目标的deepLink
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun Fragment.navigate(
    deepLink: Uri,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval) || !isFragmentAlive) return
    kotlin.runCatching {
        navController().navigate(deepLink, navOptions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的[NavDirections]导航到目的地
 *
 * @param directions 描述此导航操作的说明
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun Fragment.navigate(
    directions: NavDirections,
    navOptions: NavOptions? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval) || !isFragmentAlive) return
    kotlin.runCatching {
        navController().navigate(directions, navOptions)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的[NavDirections]导航到目的地
 *
 * @param directions 描述此导航操作的说明
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun Fragment.navigate(
    directions: NavDirections,
    navigatorExtras: Navigator.Extras,
    interval: Long = 500
) {
    if (!isValid(interval = interval) || !isFragmentAlive) return
    kotlin.runCatching {
        navController().navigate(directions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}

/**
 * 通过给定的[NavDeepLinkRequest]导航到目的地
 *
 * @param request 从当前导航图[NavGraph]可到达目标的deepLinkRequest
 * @param navOptions 此导航操作的特殊选项，可配置页面跳转动画等
 * @param navigatorExtras 额外内容传递给导航器，可配置页面共享元素等
 * @param interval 设置防抖间隔时间，单位毫秒
 */
fun Fragment.navigate(
    request: NavDeepLinkRequest,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    interval: Long = 500
) {
    if (!isValid(interval = interval) || !isFragmentAlive) return
    kotlin.runCatching {
        navController().navigate(request, navOptions, navigatorExtras)
    }.onFailure {
        it.printStackTrace()
    }
}