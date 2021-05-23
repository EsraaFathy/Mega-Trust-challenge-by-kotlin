package com.example.megatrustchallenge.dataLayer.model

import com.example.megatrustchallenge.dataLayer.onlineData.ApiInterface

class OnlineRepository(private val apiInterface : ApiInterface) {
    fun getJobs() = apiInterface.getJobsCall()
}