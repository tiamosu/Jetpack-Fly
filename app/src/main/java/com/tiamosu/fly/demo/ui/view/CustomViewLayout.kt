package com.tiamosu.fly.demo.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.LayoutCustomViewBinding
import com.tiamosu.fly.viewbinding.dataBinding

/**
 * @author ti
 * @date 2022/7/8.
 */
class CustomViewLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by dataBinding<LayoutCustomViewBinding>(attachToRoot = true)

    private val contentView by lazy {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_view, this, true)
    }

    init {
//        val binding = contentView.toViewBinding<LayoutCustomViewBinding>()
        Log.e("susu", "binding:$binding")
    }
}