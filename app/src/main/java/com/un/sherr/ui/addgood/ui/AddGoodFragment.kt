package com.un.sherr.ui.addgood.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentAddGoodBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.ui.addgood.adapters.AddPhotosAdapter
import com.un.sherr.ui.addgood.vm.AddGoodViewModel
import com.un.sherr.utils.Utils
import javax.inject.Inject

class AddGoodFragment : BaseFragment(), AddPhotosAdapter.OnPhotoClickListener, View.OnClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: AddGoodViewModel
    private lateinit var adapter: AddPhotosAdapter
    private lateinit var binding: FragmentAddGoodBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddGoodBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelProviderFactory).get(AddGoodViewModel::class.java)
        adapter = AddPhotosAdapter(requestManager, this)
        adapter.list = ArrayList(Utils.getImages(requireContext()).map { it.path })
        binding.rvPhotos.adapter = adapter
        setupListeners()
    }

    override fun onPhotoClick() {
        if (Utils.checkCameraPermission(requireContext()))
            findNavController().navigate(R.id.action_addGoodFragment_to_cameraFragment)
        val v = binding.rvPhotos.layoutManager!!.findViewByPosition(1)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.vgRubric -> {
                findNavController().navigate(R.id.action_addGoodFragment_to_rubricFragment)
            }
            R.id.btnNext -> {
                findNavController().navigate(R.id.action_addGoodFragment_to_addGood2Fragment)

            }
        }
    }

    override fun onDestroy() {
        Utils.deleteImages(requireContext())
        super.onDestroy()
    }

    private fun setupListeners() {
        binding.titleEditText.addTextChangedListener {
            viewModel.setTitle(it.toString())
        }
        binding.vgRubric.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }
}