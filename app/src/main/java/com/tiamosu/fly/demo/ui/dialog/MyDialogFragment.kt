package com.tiamosu.fly.demo.ui.dialog

import android.content.Context
import android.view.View
import com.tiamosu.fly.FlySupportDialogFragment
import com.tiamosu.fly.R
import com.tiamosu.fly.databinding.DialogMyBinding
import com.tiamosu.fly.kts.clickNoRepeat
import com.tiamosu.fly.viewbinding.viewBinding

/**
 * @author ti
 * @date 2022/7/15.
 */
class MyDialogFragment : FlySupportDialogFragment() {
    private val binding by viewBinding<DialogMyBinding>()

    override fun bindTheme(): Int {
        return R.style.ContentDialogStyle
    }

    override fun bindLayout(): Int {
        return R.layout.dialog_my
    }

    override fun initView(dialog: FlySupportDialogFragment, contentView: View) {
        dialog.isCancelable = false

        binding?.apply {
            dialogTvCancel.clickNoRepeat {
                dismiss()
            }

            dialogTvEnsure.clickNoRepeat {
                dismiss()
            }
        }
    }

    companion object {

        fun showDialog(context: Context) {
            MyDialogFragment().init(context).show()
        }
    }
}