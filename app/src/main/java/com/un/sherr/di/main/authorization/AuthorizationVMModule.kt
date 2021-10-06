package com.un.sherr.di.main.authorization

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.authorization.vm.AuthorizationViewModel
import com.un.sherr.ui.authorization.vm.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthorizationVMModule {

    @Binds
    @IntoMap
    @VMKey(AuthorizationViewModel::class)
    abstract fun postAuthorizationViewModel(viewModel: AuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(RegistrationViewModel::class)
    abstract fun postRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

}