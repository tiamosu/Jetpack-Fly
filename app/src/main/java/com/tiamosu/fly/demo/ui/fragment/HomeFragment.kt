package com.tiamosu.fly.demo.ui.fragment

import androidx.core.view.updateLayoutParams
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
                ActionType.VIEW_MODEL.type -> navigator.start(R.id.viewModelFragment)
                ActionType.VIEW_BINDING.type -> navigator.start(R.id.viewBindingFragment)
                ActionType.DIALOG_FRAGMENT.type -> MyDialogFragment.showDialog(context)
            }
        }
    }

    override fun onLazyLoad() {
        mutableListOf<HomeEntity>().apply {
            add(HomeEntity(ActionType.NAVIGATION.type, "Navigation"))
            add(HomeEntity(ActionType.ACTIVITY_RESULT.type, "ActivityResult"))
            add(HomeEntity(ActionType.VIEW_MODEL.type, "ViewModel"))
            add(HomeEntity(ActionType.VIEW_BINDING.type, "ViewBinding"))
            add(HomeEntity(ActionType.DIALOG_FRAGMENT.type, "DialogFragment"))
        }.let(adapter::setList)
    }
}