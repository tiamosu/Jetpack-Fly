package com.tiamosu.fly.delegate

import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.tiamosu.fly.FlySupportFragment

/**
 * @author ti
 * @date 2022/7/12.
 */
class FlySupportActivityDelegate(private val iFlySupport: IFlySupportActivity) :
    DefaultLifecycleObserver {
    private val activity by lazy { checkNotNull(iFlySupport as? FragmentActivity) }

    init {
        activity.lifecycle.addObserver(this)
    }

    fun onCreate() {
        iFlySupport.setContentView()
        iFlySupport.initActivity()
    }

    fun setContentView(): View? {
        val layoutId = iFlySupport.getLayoutId()
        if (layoutId > 0) {
            activity.setContentView(layoutId)
        }
        return null
    }

    fun initActivity() {
        iFlySupport.apply {
            initParameter(bundle)
            initView()
            initEvent()
            initObserver()
            loadData()

            activity.lifecycleScope.launchWhenResumed { onLazyLoad() }
        }
    }

    fun onBackPressed() {
        val activeFragment = getActiveFragment(activity.supportFragmentManager)
        if (dispatchBackPressedEvent(activeFragment) || iFlySupport.onBackPressedSupport()) {
            return
        }
        activity.onBackPressedDispatcher.onBackPressed()
    }

    fun clickBlankArea() {
        iFlySupport.hideKeyboard(activity)
    }

    fun dispatchTouchEvent(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN
            && isShouldHideKeyboard(activity.currentFocus, event)
        ) {
            clickBlankArea()
        }
    }

    private fun isShouldHideKeyboard(view: View?, event: MotionEvent): Boolean {
        if (view is EditText) {
            val l = intArrayOf(0, 0)
            view.getLocationOnScreen(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + view.height
            val right = left + view.width
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false
    }

    private fun getActiveFragment(
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

    private fun dispatchBackPressedEvent(iBackCallback: IFlyBackCallback?): Boolean {
        if (iBackCallback != null) {
            if (iBackCallback.onBackPressedSupport()) {
                return true
            }
            val parentFragment = (iBackCallback as? Fragment)?.parentFragment
            return dispatchBackPressedEvent(parentFragment as? IFlyBackCallback)
        }
        return false
    }

    override fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
        iFlySupport.removeCallbacks()
    }
}