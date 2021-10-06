package com.un.sherr.di.main.favorites

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.favorites.vm.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoritesVMModule {

    @Binds
    @IntoMap
    @VMKey(FavoritesViewModel::class)
    abstract fun postListViewModel(viewModel: FavoritesViewModel): ViewModel

}