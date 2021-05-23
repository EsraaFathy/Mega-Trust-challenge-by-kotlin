package com.example.megatrustchallenge.dataLayer.onlineData

import com.example.megatrustchallenge.dataLayer.model.Jobs
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("positions.json?description=api")
    fun getJobsCall() : Call<Jobs>
}