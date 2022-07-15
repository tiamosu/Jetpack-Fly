package com.tiamosu.fly.kts

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*

/**
 * @author tiamosu
 * @date 2022/7/8
 */
interface IAppViewModel : ViewModelStoreOwner {

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

    val appViewModelProvider: ViewModelProvider
        get() = ViewModelProvider(this)

    companion object {
        //可借助 `Application` 来管理一个应用级的 `ViewModel`，
        //实现全应用范围内的 `生命周期安全` 且 `事件源可追溯` 的 `视图控制器` 事件通知。
        val appViewModelStore = ViewModelStore()
    }
}

/**
 * 获取 Application 级别的 ViewModel
 */
inline fun <reified VM : ViewModel> LifecycleOwner.appViewModel(): Lazy<VM> {
    return lazy {
        when (this) {
            is Fragment -> {
                checkNotNull(
                    (requireActivity().application as? IAppViewModel)?.appViewModelProvider?.get(VM::class.java)
                ) { "${requireActivity().application} must impl IAppViewModel!" }
            }
            is AppCompatActivity -> {
                checkNotNull(
                    (application as? IAppViewModel)?.appViewModelProvider?.get(VM::class.java)
                ) { "$application must impl IAppViewModel!" }
            }
            else -> {
                error("${this.javaClass} must be Fragment or AppCompatActivity!")
            }
        }
    }
}