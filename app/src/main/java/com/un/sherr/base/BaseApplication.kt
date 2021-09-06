package com.un.sherr.base

import androidx.appcompat.app.AppCompatDelegate
import com.un.sherr.di.DaggerAppComponent
import com.zeugmasolutions.localehelper.LocaleHelperApplicationDelegate
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    private val localeAppDelegate = LocaleHelperApplicationDelegate()

   /* override fun attachBaseContext(newBase: Context) {
      *//*  if (newBase.getSharedPreferences("com.ab.guideness", Context.MODE_PRIVATE).getString(Constants.LANGUAGE, "").isNullOrEmpty())
            super.attachBaseContext(newBase)
        else
            super.attachBaseContext(LocaleHelper.onAttach(newBase, newBase.getSharedPreferences("com.ab.guideness", Context.MODE_PRIVATE).getString(Constants.LANGUAGE, "en")!!))
      *//*  MultiDex.install(this)

    }*/
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

}