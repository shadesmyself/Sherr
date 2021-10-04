package com.un.sherr.data.network

import android.util.Log
import com.un.sherr.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class VerificationNetworkDatabaseDataSourceImpl (val api: Api) : VerificationNetworkDatabaseDataSource {
    override suspend fun getVerification(passport: File, registration: File) {
            Log.e("NetworkDatabaseData", "getVerification: $passport")
            Log.e("NetworkDatabaseData", "getVerification: $registration")
    }
}