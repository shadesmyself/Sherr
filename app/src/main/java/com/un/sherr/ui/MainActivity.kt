package com.un.sherr.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.un.sherr.base.BaseActivity
import com.un.sherr.R
import com.un.sherr.base.BaseApplication
import com.un.sherr.databinding.ActivityMainBinding
import com.un.sherr.ui.dialog.DialogWithTwoButtons


class MainActivity : BaseActivity() {

    private var isBottomMenuVisible = true
    private lateinit var slideUp: Animation
    private lateinit var slideDown: Animation
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment))
//        /* val navHostFragment = nav_host_fragment as NavHostFragment
//         val inflater = navHostFragment.navController.navInflater
//         val graph = inflater.inflate(R.navigation.mobile_navigation)
//         navHostFragment.navController.graph = graph*/
//        binding.navView.setOnNavigationItemReselectedListener {
//
//        }
//        val source = resources.getDrawable(R.drawable.tab_home)
//        val colors = intArrayOf(
//            Color.RED, Color.BLACK )
//
//        val menu: Menu = nav_view.getMenu()
//        val shadow: Drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
//        val s: Shape = S(
//            this@MainActivity,source,
//            menu.findItem(R.id.mainPageFragment).icon, shadow, 8f
//        )
//        menu.findItem(R.id.mainPageFragment).setIcon(ShapeDrawable(s))
//        val shadow: Drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
//        val s: Shape = S(this, nav_view.itemBackground!!, nav_view.itemBackground!!, shadow, 8f)
//        nav_view.itemBackground = ShapeDrawable(s)
        binding.navView.itemIconTintList = null
        overrideNavigationMenu()
        setAnimationListeners()
    }

    private fun setAnimationListeners() {
        slideDown = AnimationUtils.loadAnimation(baseContext, R.anim.menu_slide_top)
        slideUp = AnimationUtils.loadAnimation(baseContext, R.anim.menu_slide_bot)
        slideDown.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                binding.navView.visibility = View.GONE
            }

            override fun onAnimationEnd(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}
        })

            slideUp.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                binding.navView.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    private fun overrideNavigationMenu() {
        val menu =  binding.navView.menu
        menu.findItem(R.id.chatsFragment).setOnMenuItemClickListener {
            val userToken = BaseApplication.userToken.getString("UserToken", "")
            if (userToken != "") {
                findNavController(R.id.nav_host_fragment).navigate(R.id.chatsFragment)
            } else {
                showDialog(getString(R.string.send_msg_dialog_main))
            }
            true
        }
        menu.findItem(R.id.addGoodFragment).setOnMenuItemClickListener {
            val userToken = BaseApplication.userToken.getString("UserToken", "")
            if (userToken != "") {
                findNavController(R.id.nav_host_fragment).navigate(R.id.addGoodFragment)
            } else {
                showDialog(getString(R.string.add_order_dialog))
            }
            true
        }
        menu.findItem(R.id.favoritesFragment).setOnMenuItemClickListener {
            val userToken = BaseApplication.userToken.getString("UserToken", "")
            if (userToken != "") {
                findNavController(R.id.nav_host_fragment).navigate(R.id.favoritesFragment)
            } else {
                showDialog(getString(R.string.favorites_dialog))
            }
            true
        }

        menu.findItem(R.id.myProfileFragment).setOnMenuItemClickListener {
            val userToken = BaseApplication.userToken.getString("UserToken", "")
            if (userToken != "") {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.myProfileFragment)
            } else {
                showDialog(getString(R.string.profile_dialog))
            }
            true
        }
    }

    fun showBottomMenu(show: Boolean) {
        if (show) {
            if (!isBottomMenuVisible) {
                binding.navView.startAnimation(slideUp)
            }
        } else {
            if (isBottomMenuVisible)
                binding.navView.startAnimation(slideDown)
        }
        isBottomMenuVisible = show
    }

    private fun showDialog(title: String) {
        val dialog = DialogWithTwoButtons.newInstance(
            title = title,
            firstButtonText = getString(R.string.authorization),
            secondButtonText = getString(R.string.registration)
        )
        dialog.apply {
            onFirstButtonClick = {
                findNavController(R.id.nav_host_fragment).navigate(R.id.authorizationFragment)
            }
            onSecondButtonClick = {
                findNavController(R.id.nav_host_fragment).navigate(R.id.registrationFragment)
            }
        }
        dialog.show(this.supportFragmentManager, DialogWithTwoButtons::class.java.simpleName)
    }
}
