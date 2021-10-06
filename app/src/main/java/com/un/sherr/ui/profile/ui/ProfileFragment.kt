package com.un.sherr.ui.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.custom.GridSpacingItemDecoration
import com.un.sherr.databinding.FragmentProfileBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.main.adapters.GoodsAdapter
import com.un.sherr.ui.profile.vm.ProfileViewModel
import javax.inject.Inject

class ProfileFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    private lateinit var adapter: GoodsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(ProfileViewModel::class.java)

        binding.vgComments.setOnClickListener(this)

        adapter = GoodsAdapter()
        binding.rvGoods.addItemDecoration(GridSpacingItemDecoration())
        binding.rvGoods.adapter = adapter
//        adapter.addItems(arrayListOf(Good(""), Good("dawd"), Good("dawd"), Good("dawd")))
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.vgComments -> {
                findNavController().navigate(R.id.action_profileFragment_to_commentsFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(true)
    }
}