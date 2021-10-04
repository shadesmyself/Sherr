package com.un.sherr.network

import android.content.SharedPreferences
import com.un.sherr.utils.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val token = sharedPreferences.getString(Constants.TOKEN, "")

        val builder: Request.Builder = request.newBuilder()

        if (!token.isNullOrEmpty())
            builder.addHeader("Authorization", "Bearer $token")


        request = builder.build()

        return chain.proceed(request)
    }
}