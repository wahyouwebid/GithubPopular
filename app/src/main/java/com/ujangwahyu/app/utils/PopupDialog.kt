package com.ujangwahyu.app.utils

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.ujangwahyu.app.databinding.PopupDialogBinding
import com.ujangwahyu.app.databinding.PopupDialogConfirmationBinding

class PopupDialog(private val fragment: Fragment) :
    Dialog(fragment.requireContext()) {

    fun showPopupMandatory() {
        val dialog = fragment.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        val binding: PopupDialogBinding by lazy {
            PopupDialogBinding.inflate(layoutInflater)
        }
        dialog?.setContentView(binding.root)
        val window: Window? = dialog?.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            btnPositive.setOnClickListener { dialog?.dismiss() }
        }

        dialog?.show()
    }

    fun showPopupConfirmation(
        title: String,
        subTitlePopup: String,
        titLeNegative: String,
        titleBtnPositive: String,
        actionBtnNegative: (() -> Unit?)? = null,
        actionPositive:() -> Unit,
    ) {
        val dialog = fragment.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        val binding: PopupDialogConfirmationBinding by lazy {
            PopupDialogConfirmationBinding.inflate(layoutInflater)
        }
        dialog?.setContentView(binding.root)
        val window: Window? = dialog?.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            tvTitlePopup.text = title
            tvSubtitlePopup.text = subTitlePopup
            btnNegative.text = titLeNegative
            btnPositive.text = titleBtnPositive
            btnNegative.setOnClickListener {
                actionBtnNegative?.invoke()
                dialog?.dismiss()
            }
            btnPositive.setOnClickListener {
                actionPositive.invoke()
                dialog?.dismiss()
            }
        }

        dialog?.show()
    }
}