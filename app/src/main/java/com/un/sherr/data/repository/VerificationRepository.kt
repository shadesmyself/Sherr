package com.un.sherr.data.repository

import java.io.File

interface VerificationRepository {
   suspend fun getVerification(passport: File, registration: File)
}