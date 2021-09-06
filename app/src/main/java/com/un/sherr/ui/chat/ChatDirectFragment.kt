package com.un.sherr.ui.chat


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_chat_direct.*
import javax.inject.Inject


class ChatDirectFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ChatDirectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chat_direct, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ChatDirectViewModel::class.java)

        back_btn.setOnClickListener { activity!!.onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
