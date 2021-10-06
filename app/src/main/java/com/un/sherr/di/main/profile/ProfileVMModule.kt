package com.un.sherr.di.main.profile

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.profile.vm.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileVMModule {

    @Binds
    @IntoMap
    @VMKey(ProfileViewModel::class)
    abstract fun postListViewModel(viewModel: ProfileViewModel): ViewModel

}