package com.un.sherr.di.main.chats

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.chat.ChatsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChatVMModule {

    @Binds
    @IntoMap
    @VMKey(ChatsViewModel::class)
    abstract fun postListViewModel(viewModel: ChatsViewModel): ViewModel

}