package com.tiamosu.fly.viewbinding.bind

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.tiamosu.fly.viewbinding.delegate.ViewBindingDelegate
import com.tiamosu.fly.viewbinding.kts.bindMethod
import kotlin.reflect.KProperty

/**
 * @author ti
 * @date 2022/7/11.
 */
open class ViewHolderViewBinding<V : ViewBinding>(
    private val bindingClass: Class<V>,
    viewHolder: RecyclerView.ViewHolder
) : ViewBindingDelegate<RecyclerView.ViewHolder, V>(viewHolder) {
    private val bindMethod by lazy { bindingClass.bindMethod() }

    override fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): V? {
        binding?.let { return it }

        @Suppress("UNCHECKED_CAST")
        binding = bindMethod.invoke(null, thisRef.itemView) as? V
        return binding
    }
}