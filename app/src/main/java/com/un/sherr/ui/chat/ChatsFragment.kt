package com.un.sherr.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.models.Chat
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.chat.adapters.ChatsAdapter
import kotlinx.android.synthetic.main.fragment_chat.*
import javax.inject.Inject


class ChatsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var adapter: ChatsAdapter
    private lateinit var viewModel: ChatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chat, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ChatsViewModel::class.java)
        adapter = ChatsAdapter()
        rvChats.adapter = adapter
        adapter.onItemClick = {
            findNavController().navigate(R.id.action_chatsFragment_to_chatDirectFragment)
        }
        adapter.addItems(mutableListOf(Chat(""), Chat(""), Chat(""), Chat(""), Chat(""), Chat("")))

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(true)
    }
}
