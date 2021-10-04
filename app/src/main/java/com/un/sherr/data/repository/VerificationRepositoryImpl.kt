package com.un.sherr.data.repository

import com.un.sherr.data.network.VerificationNetworkDatabaseDataSource
import java.io.File

class VerificationRepositoryImpl(private val networkDatabaseDataSource: VerificationNetworkDatabaseDataSource) :
    VerificationRepository {
    override suspend fun getVerification(passport: File, registration: File) =
        networkDatabaseDataSource.getVerification(passport, registration)
}