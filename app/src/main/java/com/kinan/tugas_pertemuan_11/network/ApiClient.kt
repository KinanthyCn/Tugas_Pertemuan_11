package com.kinan.tugas_pertemuan_11.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance() : ApiService {
        val mOkHttpInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val mOkkHttpClient = OkHttpClient.Builder()
            .addInterceptor(mOkHttpInterceptor)
            .build()
        val builder = Retrofit.Builder()
            .baseUrl("https://random-d.uk/api/v2/")
            .client(mOkkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(ApiService::class.java)
    }
}