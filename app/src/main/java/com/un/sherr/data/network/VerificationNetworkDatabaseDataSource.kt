package com.un.sherr.data.network

import java.io.File

interface VerificationNetworkDatabaseDataSource {
    suspend fun getVerification(passport: File, registration: File)
}