package com.tiamosu.fly.demo.ui.fragment

import android.util.Log
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.IntentUtils
import com.blankj.utilcode.util.ToastUtils
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.FragmentHomeBinding
import com.tiamosu.fly.demo.base.BaseFragment
import com.tiamosu.fly.demo.data.enum.ActionType
import com.tiamosu.fly.demo.data.model.HomeEntity
import com.tiamosu.fly.demo.kts.init
import com.tiamosu.fly.demo.kts.statusBarHeight
import com.tiamosu.fly.demo.ui.adapter.HomeAdapter
import com.tiamosu.fly.demo.ui.dialog.MyDialogFragment
import com.tiamosu.fly.kts.launchWhenLazyResumed
import com.tiamosu.fly.kts.startForActivityResult
import com.tiamosu.fly.navigation.navigator
import com.tiamosu.fly.navigation.start
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/8.
 */
class HomeFragment : BaseFragment() {
    private val binding by viewBinding<FragmentHomeBinding>()
    private val adapter by lazy { HomeAdapter() }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        binding?.homeViewTitleBar?.updateLayoutParams { height = statusBarHeight }
        binding?.homeRv?.init(bindAdapter = adapter)

        lifecycleScope.launchWhenResumed {
            Log.e("susu2", "launchWhenResumed")
        }

        launchWhenLazyResumed {
            Log.e("susu2", "launchWhenLazyResumed")
        }
        launchWhenLazyResumed {
            Log.e("susu2", "launchWhenLazyResumed2")
        }
    }

    override fun initEvent() {
        adapter.setOnItemClickListener { _, _, position ->
            val entity = adapter.data[position]
            when (entity.type) {
                ActionType.NAVIGATION.type -> navigator.start(R.id.aFragment)
                ActionType.ACTIVITY_RESULT.type -> startForActivityResult(
                    IntentUtils.getLaunchAppDetailsSettingsIntent(AppUtils.getAppPackageName())
                ) {
                    ToastUtils.showLong(it.toString())
                }
                ActionType.MVI_DISPATCHER.type -> navigator.start(R.id.mviFragment)
                ActionType.VIEW_BINDING.type -> navigator.start(R.id.bindingFragment)
                ActionType.DIALOG_FRAGMENT.type -> MyDialogFragment.showDialog(context)
                ActionType.SHARED.type -> navigator.start(R.id.sharedFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("susu2", "onResume")
    }

    override fun onSupportVisible() {
        Log.e("susu2", "onSupportVisible:${isSupportVisible()}")
    }

    override fun onSupportInvisible() {
        Log.e("susu", "onSupportInvisible:${isSupportVisible()}")
    }

    override fun onLazyLoad() {
        Log.e("susu2", "onLazyLoad:${isSupportVisible()}")

        launchWhenLazyResumed {
            Log.e("susu2", "launchWhenLazyResumed --- onLazyLoad")
        }

        mutableListOf<HomeEntity>().apply {
            add(HomeEntity(ActionType.NAVIGATION.type, "Navigation"))
            add(HomeEntity(ActionType.ACTIVITY_RESULT.type, "ActivityResult"))
            add(HomeEntity(ActionType.MVI_DISPATCHER.type, "MVI-Dispatcher"))
            add(HomeEntity(ActionType.VIEW_BINDING.type, "ViewBinding"))
            add(HomeEntity(ActionType.DIALOG_FRAGMENT.type, "DialogFragment"))
            add(HomeEntity(ActionType.SHARED.type, "SharedViewModel"))
        }.let(adapter::setList)
    }
}