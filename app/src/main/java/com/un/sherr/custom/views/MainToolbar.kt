package com.un.sherr.custom.views

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.appbar.AppBarLayout
import com.un.sherr.R
import com.un.sherr.databinding.MainToolbarBinding

class MainToolbar(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {

    private val text: String
    private val drawable: Int

    private var binding: MainToolbarBinding =
        MainToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MainToolbar,
            0, 0
        ).apply {
            try {
                text = if (getString(R.styleable.MainToolbar_text) != null)
                    getString(R.styleable.MainToolbar_text)!!
                else
                    ""
                drawable = getResourceId(R.styleable.MainToolbar_resource, 0)
                initView()
            } finally {
                recycle()
            }
        }
    }

    fun onIconClick(listener: OnClickListener){
        binding.mainToolbarImage.setOnClickListener(listener)
    }

    private fun initView() {
        binding.mainToolbarImage.setOnClickListener{(context as Activity).onBackPressed()}
        binding.mainToolbarTitle.text = text
        binding.mainToolbarImage.setImageResource(drawable)
    }

    fun setTitle(title: String) {
        binding.mainToolbarTitle.text = title
    }
}