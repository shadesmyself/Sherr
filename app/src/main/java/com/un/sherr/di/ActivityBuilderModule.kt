package com.un.sherr.di

import com.un.sherr.ui.MainActivity
import com.un.sherr.di.main.MainFragmentBuildersModule
import com.un.sherr.di.main.MainModule
import com.un.sherr.di.main.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(
            MainFragmentBuildersModule::class,
            MainModule::class
        )
    )
    abstract fun contributeMainActivity(): MainActivity
}
