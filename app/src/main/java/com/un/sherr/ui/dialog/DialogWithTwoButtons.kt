package com.un.sherr.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.un.sherr.R
import com.un.sherr.databinding.DialogWithTwoButtonsBinding

class DialogWithTwoButtons(
    private val title: String,
    private val firstButtonText: String,
    private val secondButtonText: String
) : DialogFragment() {

    var onFirstButtonClick: (() -> Unit)? = null
    var onSecondButtonClick: (() -> Unit)? = null

    private lateinit var binding: DialogWithTwoButtonsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.background_dialog_fragment)
        binding = DialogWithTwoButtonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.description.text = title
        binding.firstButton.text = firstButtonText
        binding.secondButton.text = secondButtonText
        setupListeners()
    }

    private fun setupListeners() {
        binding.firstButton.setOnClickListener {
            onFirstButtonClick?.invoke()
            dialog?.cancel()
        }
        binding.secondButton.setOnClickListener {
            onSecondButtonClick?.invoke()
            dialog?.cancel()
        }
    }

    companion object {
        fun newInstance(title: String, firstButtonText: String, secondButtonText: String) =
            DialogWithTwoButtons(title, firstButtonText, secondButtonText)
    }
}