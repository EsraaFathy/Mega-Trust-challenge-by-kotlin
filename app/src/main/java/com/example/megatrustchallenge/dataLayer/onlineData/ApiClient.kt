package com.example.megatrustchallenge.dataLayer.onlineData

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object ApiClient {
    private const val BASE_URL = "https://jobs.github.com/"
    var gson = GsonBuilder()
            .setLenient()
            .create()
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create()) //important
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    val apiService: ApiInterface = getRetrofit().create(ApiInterface::class.java)
}