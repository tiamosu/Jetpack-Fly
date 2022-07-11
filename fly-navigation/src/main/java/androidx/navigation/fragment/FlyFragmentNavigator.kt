package androidx.navigation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.util.*

/**
 * @author ti
 * @date 2022/7/6.
 */
@Navigator.Name("fragment")
class FlyFragmentNavigator constructor(
    private val context: Context,
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, fragmentManager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        if (fragmentManager.isStateSaved) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state")
            return null
        }
        var className = destination.className
        if (className[0] == '.') {
            className = context.packageName + className
        }

        val ft = fragmentManager.beginTransaction()
        var enterAnim = navOptions?.enterAnim ?: -1
        var exitAnim = navOptions?.exitAnim ?: -1
        var popEnterAnim = navOptions?.popEnterAnim ?: -1
        var popExitAnim = navOptions?.popExitAnim ?: -1
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            enterAnim = if (enterAnim != -1) enterAnim else 0
            exitAnim = if (exitAnim != -1) exitAnim else 0
            popEnterAnim = if (popEnterAnim != -1) popEnterAnim else 0
            popExitAnim = if (popExitAnim != -1) popExitAnim else 0
            ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        }

        var backStack = ArrayDeque<Int>()
        kotlin.runCatching {
            val field = FragmentNavigator::class.java.getDeclaredField("mBackStack")
            field.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            backStack = field[this] as? ArrayDeque<Int> ?: ArrayDeque()
        }
        val initialNavigation = backStack.isEmpty()
        @IdRes val destId = destination.id
        // TODO Build first class singleTop behavior for fragments
        val isSingleTopReplacement = !initialNavigation
                && navOptions?.shouldLaunchSingleTop() == true
                && backStack.peekLast() == destId

        if (backStack.isNotEmpty() && fragmentManager.fragments.isNotEmpty()) {
            val hideFrag = fragmentManager.primaryNavigationFragment

            if (isSingleTopReplacement) {
                hideFrag?.let { hideF ->
                    hideF.arguments = args
                    ft.setMaxLifecycle(hideF, Lifecycle.State.RESUMED)
                }
            } else {
                hideFrag?.let { hideF ->
                    ft.setMaxLifecycle(hideF, Lifecycle.State.STARTED)
                    ft.hide(hideF)
                }

                val tag = destination.id.toString()
                var frag = fragmentManager.findFragmentByTag(tag)
                if (frag != null) {
                    ft.setMaxLifecycle(frag, Lifecycle.State.RESUMED)
                    ft.show(frag)
                } else {
                    @Suppress("DEPRECATION")
                    frag = instantiateFragment(context, fragmentManager, className, args)
                    frag.arguments = args
                    ft.add(containerId, frag, tag)
                }
                ft.setPrimaryNavigationFragment(frag)
            }
        } else {
            @Suppress("DEPRECATION")
            val frag = instantiateFragment(context, fragmentManager, className, args)
            frag.arguments = args
            ft.replace(containerId, frag)
            ft.setPrimaryNavigationFragment(frag)
        }

        val isAdded = if (initialNavigation) {
            true
        } else if (isSingleTopReplacement) {
            false
        } else {
            ft.addToBackStack(generateBackStackName(backStack.size + 1, destId))
            true
        }
        if (navigatorExtras is Extras) {
            for ((key, value) in navigatorExtras.sharedElements) {
                ft.addSharedElement(key, value)
            }
        }
        ft.setReorderingAllowed(true)
        ft.commit()
        // The commit succeeded, update our view of the world
        return if (isAdded) {
            backStack.add(destId)
            destination
        } else {
            null
        }
    }

    private fun generateBackStackName(backStackIndex: Int, destId: Int?): String {
        return "${backStackIndex}-${destId ?: 0}"
    }

    companion object {
        private const val TAG = "FragmentNavigator"
    }
}