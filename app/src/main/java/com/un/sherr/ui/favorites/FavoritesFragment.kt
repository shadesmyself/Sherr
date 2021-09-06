package com.un.sherr.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.un.sherr.R
import com.un.sherr.base.BaseFragment
import com.un.sherr.ui.favorites.adapters.FavoritesVPAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpFavorites.adapter = FavoritesVPAdapter()
        tvAnnouncement.setOnClickListener(this)
        tvSearch.setOnClickListener(this)

        vpFavorites.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    tvAnnouncement.setBackgroundResource(R.drawable.orange_btn)
                    tvSearch.setBackgroundResource(R.drawable.gray_btn)
                } else {
                    tvAnnouncement.setBackgroundResource(R.drawable.gray_btn)
                    tvSearch.setBackgroundResource(R.drawable.orange_btn)
                }
            }
        })

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvAnnouncement -> {
                tvAnnouncement.setBackgroundResource(R.drawable.orange_btn)
                tvSearch.setBackgroundResource(R.drawable.gray_btn)
                vpFavorites.currentItem = 0
            }
            R.id.tvSearch -> {
                tvAnnouncement.setBackgroundResource(R.drawable.gray_btn)
                tvSearch.setBackgroundResource(R.drawable.orange_btn)
                vpFavorites.currentItem = 1
            }
        }
    }


}
