package com.un.sherr.di.main.chats

import androidx.lifecycle.ViewModel
import com.un.sherr.di.VMKey
import com.un.sherr.ui.chat.ChatDirectViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChatDirectVMModule {

    @Binds
    @IntoMap
    @VMKey(ChatDirectViewModel::class)
    abstract fun postListViewModel(viewModel: ChatDirectViewModel): ViewModel

}