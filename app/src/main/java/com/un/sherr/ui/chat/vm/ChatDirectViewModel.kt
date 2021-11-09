package com.un.sherr.ui.chat.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.un.sherr.base.BaseViewModelCoroutines
import com.un.sherr.models.ChatDirectBase
import com.un.sherr.models.ChatDirectMain
import com.un.sherr.models.ChatDirectResponse
import com.un.sherr.models.DataWrapperResponse
import com.un.sherr.network.Api
import retrofit2.Response
import javax.inject.Inject

class ChatDirectViewModel @Inject constructor(val api: Api) : BaseViewModelCoroutines(){

    val chatDirectResponse = MutableLiveData<Response<DataWrapperResponse<List<ChatDirectMain>>>>()
    fun sendData(token: String){
        runOperation {
            work {
                chatDirectResponse.postValue(getData(token))
            }
        }
    }
    private suspend fun getData(token: String) : Response<DataWrapperResponse<List<ChatDirectMain>>> {
        return api.getChatDirect(1, "Bearer $token")
    }

    fun sendMessage(token: String, message: String){
        runOperation {
            work {
                sendMessageEdt(token, message)
            }
        }
    }

    private suspend fun sendMessageEdt(token: String, message: String): Response<Unit>{
        Log.e("Message", "sendMessageEdt: $message ")
        Log.e("Message", "sendMessageEdt: $token ")
        return api.sendMessage("Bearer $token",2, message)
    }

}