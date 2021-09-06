package com.un.sherr.di.main

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.un.sherr.R
import com.un.sherr.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @MainScope
    @Provides
    fun provideNavigationController(mainActivity: MainActivity): NavController {
        return Navigation.findNavController(mainActivity, R.id.nav_host_fragment)
//        return mainActivity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
    }
}
