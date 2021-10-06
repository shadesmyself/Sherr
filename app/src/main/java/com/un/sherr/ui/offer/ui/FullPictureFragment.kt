package com.un.sherr.ui.offer.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentPictureBinding
import com.un.sherr.ui.MainActivity

class FullPictureFragment : BaseFragment() {

    companion object {
        val IMG: String = "IMG"
    }

    private lateinit var binding: FragmentPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View{
        binding = FragmentPictureBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestManager
            .load(requireArguments().get(IMG))
            .into(binding.img)

        binding.backBtn.setOnClickListener { requireActivity().onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}
