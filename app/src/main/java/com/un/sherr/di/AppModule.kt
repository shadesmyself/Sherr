package com.un.sherr.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.un.sherr.R
import com.un.sherr.network.Api
import com.un.sherr.network.ErrorInterceptor
import com.un.sherr.network.HeaderInterceptor
import com.un.sherr.utils.Constants
import com.un.sherr.utils.MapManagement
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(sharedPreferences: SharedPreferences): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                var request = chain.request()
                val url = request.url.newBuilder().build()
                 request = request.newBuilder().url(url).build()
                chain.proceed(request)
            })
            .addInterceptor(interceptor)
            .addInterceptor(ErrorInterceptor())
            .addInterceptor(HeaderInterceptor(sharedPreferences))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMainApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.place_holder) // any placeholder to load at start
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(R.drawable.place_holder)

    }

    @Singleton
    @Provides
    fun provideGlideInstance(app: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(app)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun getMapManagement(): MapManagement {
        return MapManagement()
    }

    @Singleton
    @Provides
    fun getDefaultSharadPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("com.un.loop", Context.MODE_PRIVATE)
    }
}
