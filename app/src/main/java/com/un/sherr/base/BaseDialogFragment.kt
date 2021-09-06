package com.un.sherr.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.un.sherr.R

abstract class BaseDialogFragment: DialogFragment() {

    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.background_dialog_fragment)
        return view
    }
}