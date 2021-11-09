package com.un.sherr.ui.chat.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.base.BaseApplication
import com.un.sherr.databinding.FragmentChatBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.models.Chat
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.chat.adapters.ChatsAdapter
import com.un.sherr.ui.chat.vm.ChatsViewModel
import javax.inject.Inject

class ChatsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var adapter: ChatsAdapter
    private lateinit var viewModel: ChatsViewModel
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ChatsViewModel::class.java)
        val userToken = BaseApplication.userToken.getString("UserToken", "")
        viewModel.sendData(userToken!!)
        adapter = ChatsAdapter()
        viewModel.chatsData.observe(viewLifecycleOwner, {
            Log.e("TAG", "onViewCreated: ${it.body()!!.last_message}", )
            binding.rvChats.adapter = adapter
            adapter.onItemClick = {
                findNavController().navigate(R.id.action_chatsFragment_to_chatDirectFragment)
            }
            adapter.addItems(mutableListOf(Chat(""), Chat(""), Chat(""), Chat(""), Chat(""), Chat("")))

        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(true)
    }
}
