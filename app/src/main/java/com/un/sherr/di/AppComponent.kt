package com.un.sherr.di

import android.app.Application
import android.content.SharedPreferences
import com.un.sherr.base.BaseApplication
import com.un.sherr.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityBuilderModule::class, AppModule::class, ViewModelFuctoryModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager
    fun sharedPreferences(): SharedPreferences

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}