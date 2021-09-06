package com.un.sherr.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.un.sherr.base.BaseActivity
import com.un.sherr.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var isBottomMenuVisible = true
    private lateinit var slideUp: Animation
    private lateinit var slideDown: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment))
//        /* val navHostFragment = nav_host_fragment as NavHostFragment
//         val inflater = navHostFragment.navController.navInflater
//         val graph = inflater.inflate(R.navigation.mobile_navigation)
//         navHostFragment.navController.graph = graph*/
//
        nav_view.setOnNavigationItemReselectedListener { }
        nav_view.itemIconTintList = null

//        val source = resources.getDrawable(R.drawable.tab_home)
//        val colors = intArrayOf(
//            Color.RED, Color.BLACK
//        )
//
//        val menu: Menu = nav_view.getMenu()
//        val shadow: Drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
//        val s: Shape = S(
//            this@MainActivity,source,
//            menu.findItem(R.id.mainPageFragment).icon, shadow, 8f
//        )
//
//
//        menu.findItem(R.id.mainPageFragment).setIcon(ShapeDrawable(s))

//        val shadow: Drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
//        val s: Shape = S(this, nav_view.itemBackground!!, nav_view.itemBackground!!, shadow, 8f)
//        nav_view.itemBackground = ShapeDrawable(s)

        setAnimationListeners()
    }

    private fun setAnimationListeners() {
        slideDown = AnimationUtils.loadAnimation(baseContext, R.anim.menu_slide_top)
        slideUp = AnimationUtils.loadAnimation(baseContext, R.anim.menu_slide_bot)
        slideDown.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                nav_view.visibility = View.GONE
            }

            override fun onAnimationEnd(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}
        })

        slideUp.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                nav_view.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    fun showBottomMenu(show: Boolean) {
        if (show) {
            if (!isBottomMenuVisible) {
                nav_view.startAnimation(slideUp)
            }
        } else {
            if (isBottomMenuVisible)
                nav_view.startAnimation(slideDown)
        }
        isBottomMenuVisible = show
    }

}
