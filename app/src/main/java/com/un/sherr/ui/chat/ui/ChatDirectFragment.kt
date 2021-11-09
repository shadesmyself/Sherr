package com.un.sherr.ui.chat.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.un.sherr.base.BaseApplication
import com.un.sherr.base.BaseFragment
import com.un.sherr.custom.GridSpacingItemDecoration
import com.un.sherr.databinding.FragmentChatDirectBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.chat.adapters.ChatsDirectAdapter
import com.un.sherr.ui.chat.vm.ChatDirectViewModel
import com.un.sherr.ui.main.adapters.GoodsAdapter
import com.un.sherr.ui.main.ui.MainFragmentDirections
import kotlinx.android.synthetic.main.fragment_chat_direct.*
import javax.inject.Inject

class ChatDirectFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ChatDirectViewModel
    private lateinit var binding: FragmentChatDirectBinding
    private var userToken: String? = null
    private lateinit var mainAdapter: ChatsDirectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatDirectBinding.inflate(layoutInflater)
        setupGoodsRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ChatDirectViewModel::class.java)

        userToken = BaseApplication.userToken.getString("UserToken", "")
        viewModel.sendData(userToken!!)
        viewModel.chatDirectResponse.observe(viewLifecycleOwner,{
            mainAdapter.addItems(it.body()!!.data!!.toMutableList())
        })
        sendMessage()
        binding.backBtn.setOnClickListener { requireActivity().onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

    private fun sendMessage() {
        binding.btnSendMessage.setOnClickListener {
            val message = binding.etMessage.text.toString()
            if (message.isNotEmpty()){
                viewModel.sendMessage(userToken!!, message)
                binding.etMessage.text.clear()
            }
        }
    }

    private fun setupGoodsRecycler() {
        mainAdapter = ChatsDirectAdapter()
        binding.rvChat.adapter = mainAdapter
        binding.rvChat.layoutManager = LinearLayoutManager(requireContext())
    }
}
