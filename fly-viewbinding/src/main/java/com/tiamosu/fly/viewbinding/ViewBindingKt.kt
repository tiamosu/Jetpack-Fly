package com.tiamosu.fly.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.Utils
import com.tiamosu.fly.viewbinding.bind.*
import com.tiamosu.fly.viewbinding.kts.bindMethod

/**
 * @author ti
 * @date 2022/7/11.
 */

val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(Utils.getApp()) }

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call
 * private val binding by viewBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewBinding> ComponentActivity.viewBinding() =
    ActivityViewBinding(V::class.java, this)

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call
 * private val binding by dataBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewDataBinding> ComponentActivity.dataBinding() =
    ActivityDataBinding(V::class.java, this)

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call
 * private val binding by viewBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewBinding> Fragment.viewBinding() =
    FragmentViewBinding(V::class.java, this)

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call
 * private val binding by dataBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewDataBinding> Fragment.dataBinding() =
    FragmentDataBinding(V::class.java, this)

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding by viewBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewBinding> ViewGroup.viewBinding(attachToRoot: Boolean = false) =
    ViewGroupViewBinding(
        V::class.java,
        viewGroup = this,
        attachToRoot = attachToRoot
    )

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding by dataBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewDataBinding> ViewGroup.dataBinding(attachToRoot: Boolean = false) =
    ViewGroupViewBinding(
        V::class.java,
        viewGroup = this,
        attachToRoot = attachToRoot
    )

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding by viewBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewBinding> RecyclerView.ViewHolder.viewBinding() =
    ViewHolderViewBinding(V::class.java, this)

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding by dataBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewDataBinding> RecyclerView.ViewHolder.dataBinding() =
    ViewHolderViewBinding(V::class.java, this)

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding = contentView.toViewBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
inline fun <reified V : ViewBinding> View.toViewBinding(): V? {
    val bindMethod = V::class.java.bindMethod()
    return bindMethod.invoke(null, this) as? V
}

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding = contentView.toDataBinding<ExampleBinding>()
 * with your binding class and access it as you normally would.
 */
fun <V : ViewDataBinding> View.toDataBinding(): V? {
    return DataBindingUtil.bind(this)
}