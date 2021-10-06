package com.un.sherr.di.main.main

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.main.vm.FilterViewModel
import com.un.sherr.ui.main.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainVMModule {

    @Binds
    @IntoMap
    @VMKey(MainViewModel::class)
    abstract fun postListViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(FilterViewModel::class)
    abstract fun postFilterViewModel(viewModel: FilterViewModel): ViewModel
}