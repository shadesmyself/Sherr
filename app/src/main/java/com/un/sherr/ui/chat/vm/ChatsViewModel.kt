package com.un.sherr.ui.chat.vm

import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModelCoroutines
import com.un.sherr.models.Chats
import com.un.sherr.network.Api
import retrofit2.Response
import javax.inject.Inject

class ChatsViewModel @Inject constructor(var api: Api) : BaseViewModelCoroutines() {

    val chatsData = MutableLiveData<Response<Chats>>()

    fun sendData(token: String) {
        runOperation {
            work {
                chatsData.postValue(getData(token))
            }
        }
    }

    private suspend fun getData(token: String) : Response<Chats>{
     return api.getAllChats("Bearer $token")
    }
}