package com.m.appx.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    val instance : IApi by lazy {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(
            HttpLoggingInterceptor.Level.BODY // set your desired log level : NONE, BASIC, HEADERS, BODY
        )

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        return@lazy retrofit.create(IApi::class.java)
    }
}