package com.un.sherr.ui.dialog

import android.os.Bundle
import android.view.View
import com.un.sherr.R
import com.un.sherr.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_with_two_buttons.*

class DialogWithTwoButtons(
    private val title: String,
    private val firstButtonText: String,
    private val secondButtonText: String
) : BaseDialogFragment() {

    override val layoutId = R.layout.dialog_with_two_buttons

    var onFirstButtonClick: (() -> Unit)? = null
    var onSecondButtonClick: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description.text = title
        first_button.text = firstButtonText
        second_button.text = secondButtonText
        setupListeners()
    }

    private fun setupListeners() {
        first_button.setOnClickListener {
            onFirstButtonClick?.invoke()
            dialog?.cancel()
        }
        second_button.setOnClickListener {
            onSecondButtonClick?.invoke()
            dialog?.cancel()
        }
    }

    companion object {
        fun newInstance(title: String, firstButtonText: String, secondButtonText: String) =
            DialogWithTwoButtons(title, firstButtonText, secondButtonText)
    }
}