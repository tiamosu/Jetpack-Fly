package com.tiamosu.fly

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ViewUtils
import com.tiamosu.fly.delegate.IFlySupportDialogFragment

/**
 * @author ti
 * @date 2022/7/15.
 */
abstract class FlySupportDialogFragment : DialogFragment(), IFlySupportDialogFragment {
    private val logTag by lazy { this::class.java.simpleName }

    private var fragmentActivity: FragmentActivity? = null

    /**
     * 初始化调用
     */
    fun init(context: Context): FlySupportDialogFragment {
        val activity = ActivityUtils.getActivityByContext(context)
        if (activity !is FragmentActivity) {
            Log.e(logTag, "${context::class.java} must be FragmentActivity")
        } else {
            fragmentActivity = activity
        }
        return this
    }

    /**
     * 展示弹框
     */
    fun show(tag: String? = javaClass.simpleName) {
        ViewUtils.runOnUiThread {
            if (!ActivityUtils.isActivityAlive(fragmentActivity)) return@runOnUiThread
            kotlin.runCatching {
                fragmentActivity?.supportFragmentManager?.apply {
                    findFragmentByTag(tag)?.also {
                        beginTransaction().remove(it)
                    }
                    show(this, tag)
                }
            }
        }
    }

    /**
     * 关闭弹框
     */
    override fun dismiss() {
        ViewUtils.runOnUiThread {
            if (!ActivityUtils.isActivityAlive(fragmentActivity)) return@runOnUiThread
            kotlin.runCatching {
                dismissAllowingStateLoss()
            }
        }
    }

    override fun getTheme(): Int {
        return if (bindTheme() != View.NO_ID) bindTheme() else super.getTheme()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when {
            bindLayout() > 0 -> inflater.inflate(bindLayout(), container, false)
            else -> null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(this, view)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            dialog?.window?.let { setWindowStyle(it) }
        }
    }
}