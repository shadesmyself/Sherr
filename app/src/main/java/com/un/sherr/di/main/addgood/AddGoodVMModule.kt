package com.un.sherr.di.main.addgood

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.addgood.AddGoodViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddGoodVMModule {
    @Binds
    @IntoMap
    @VMKey(AddGoodViewModel::class)
    abstract fun postListViewModel(viewModel: AddGoodViewModel): ViewModel

}