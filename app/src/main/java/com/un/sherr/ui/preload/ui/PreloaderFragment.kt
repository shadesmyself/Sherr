package com.un.sherr.ui.preload.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.un.sherr.R
import com.un.sherr.databinding.FragmentPreloaderBinding
import com.un.sherr.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PreloaderFragment : Fragment() {
    private lateinit var binding: FragmentPreloaderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreloaderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       lifecycleScope.launch {
           delay(3000)
           findNavController().navigate(R.id.mainPageFragment)
       }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }
}