package com.tiamosu.fly.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tiamosu.fly.FlySupportFragment
import com.tiamosu.fly.delegate.IFlyBackCallback

/**
 * @author ti
 * @date 2022/12/5.
 */
object FlySupportUtils {

    fun getActiveFragment(
        fragmentManager: FragmentManager,
        parentFragment: IFlyBackCallback? = null
    ): IFlyBackCallback? {
        val fragments = fragmentManager.fragments
        if (fragments.isEmpty()) return parentFragment
        for (i in fragments.indices.reversed()) {
            val fragment = fragments[i]
            if (isNavHostFragment(fragment)
                || (fragment as? FlySupportFragment)?.isResumed == true
            ) {
                return getActiveFragment(
                    fragment.childFragmentManager,
                    fragment as? IFlyBackCallback
                )
            }
        }
        return parentFragment
    }

    private fun isNavHostFragment(fragment: Fragment?): Boolean {
        return fragment?.javaClass?.simpleName?.contains(
            "NavHostFragment",
            ignoreCase = true
        ) == true
    }
}