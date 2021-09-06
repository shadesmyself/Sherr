package com.un.sherr.custom.views

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.un.sherr.R
import kotlinx.android.synthetic.main.main_toolbar.view.*

class MainToolbar(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {

    private val text: String
    private val drawable: Int

    init {
        View.inflate(context, R.layout.main_toolbar, this)

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
        mainToolbarImage.setOnClickListener(listener)
    }

    private fun initView() {
        mainToolbarImage.setOnClickListener{(context as Activity).onBackPressed()}
        mainToolbarTitle.text = text
        mainToolbarImage.setImageResource(drawable)
    }

    fun setTitle(title: String) {
        mainToolbarTitle.text = title
    }
}