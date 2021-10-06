package com.un.sherr.ui.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.databinding.FragmentFavoritesBinding
import com.un.sherr.ui.favorites.adapters.FavoritesVPAdapter

class FavoritesFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vpFavorites.adapter = FavoritesVPAdapter()
        binding.tvAnnouncement.setOnClickListener(this)
        binding.tvSearch.setOnClickListener(this)

        binding.vpFavorites.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.tvAnnouncement.setBackgroundResource(R.drawable.orange_btn)
                    binding.tvSearch.setBackgroundResource(R.drawable.gray_btn)
                } else {
                    binding.tvAnnouncement.setBackgroundResource(R.drawable.gray_btn)
                    binding.tvSearch.setBackgroundResource(R.drawable.orange_btn)
                }
            }
        })

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvAnnouncement -> {
                binding.tvAnnouncement.setBackgroundResource(R.drawable.orange_btn)
                binding.tvSearch.setBackgroundResource(R.drawable.gray_btn)
                binding.vpFavorites.currentItem = 0
            }
            R.id.tvSearch -> {
                binding.tvAnnouncement.setBackgroundResource(R.drawable.gray_btn)
                binding.tvSearch.setBackgroundResource(R.drawable.orange_btn)
                binding.vpFavorites.currentItem = 1
            }
        }
    }


}
