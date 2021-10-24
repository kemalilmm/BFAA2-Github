package com.kimm.bfaa2_github.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization","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" ) // REPLACE GITHUB_API_KEY WITH YOUR GITHUB API KEY
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    private val retrofit : Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)

    }
    val apiInstance :RetrofitInterface by lazy{
        retrofit.build().create(RetrofitInterface::class.java)
    }
}