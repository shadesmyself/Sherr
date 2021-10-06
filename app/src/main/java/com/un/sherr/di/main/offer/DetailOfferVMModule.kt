package com.un.sherr.di.main.offer

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.offer.vm.DetailOfferViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailOfferVMModule {

    @Binds
    @IntoMap
    @VMKey(DetailOfferViewModel::class)
    abstract fun postDetailOfferViewModel(viewModel: DetailOfferViewModel): ViewModel

}