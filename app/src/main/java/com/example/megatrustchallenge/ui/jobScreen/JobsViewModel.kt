package com.example.megatrustchallenge.ui.jobScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megatrustchallenge.dataLayer.model.Jobs
import com.example.megatrustchallenge.dataLayer.model.OnlineRepository
import com.example.megatrustchallenge.dataLayer.onlineData.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel : ViewModel() {
    var jobsData : MutableLiveData<Jobs> = MutableLiveData()
    var progressBar : MutableLiveData<Boolean> = MutableLiveData()
    private val onlineRepository = OnlineRepository(ApiClient.apiService)

    fun getJobsData(){
        CoroutineScope(Dispatchers.IO).launch {
            onlineRepository.getJobs().enqueue(object : Callback<Jobs?> {
                override fun onResponse(call: Call<Jobs?>, response: Response<Jobs?>) {
                    if (response.isSuccessful){
                        jobsData.postValue(response.body())
                        progressBar.postValue(false)
                    }
                }

                override fun onFailure(call: Call<Jobs?>, t: Throwable) {
                    Log.d("tag", t.message.toString())
                    t.printStackTrace()

                }
            })
        }
    }
}