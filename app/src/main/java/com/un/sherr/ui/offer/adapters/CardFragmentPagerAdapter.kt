package com.un.sherr.ui.offer.adapters

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.un.sherr.ui.offer.PictureFragment


class CardFragmentPagerAdapter( fm: FragmentManager?, baseElevation: Float, var img: List<String>) :
    FragmentStatePagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT), CardAdapter {
    private val fragments: MutableList<PictureFragment>
    override val baseElevation: Float
    override val size: Int
        get() =  img.size

    override fun getCardViewAt(position: Int): CardView? {
        return fragments[position].mCardView
    }

    override fun getCount(): Int {
        return img.size
    }

    override fun getItem(position: Int): Fragment {
        return PictureFragment.getInstance(img[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        fragments[position] = fragment as PictureFragment
        return fragment
    }

    private fun addCardFragment(fragment: PictureFragment) {
        fragments.add(fragment)
    }

    init {
        fragments = ArrayList()
        this.baseElevation = baseElevation
        for (i in 0..img.size) {
            addCardFragment(PictureFragment())
        }
    }
}