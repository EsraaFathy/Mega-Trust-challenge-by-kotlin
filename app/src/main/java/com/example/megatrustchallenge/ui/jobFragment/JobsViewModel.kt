package com.example.megatrustchallenge.ui.jobFragment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megatrustchallenge.dataLayer.model.Jobs
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.dataLayer.model.OnlineRepository
import com.example.megatrustchallenge.dataLayer.onlineData.ApiClient
import com.example.megatrustchallenge.dataLayer.room.RoomReposatory
import com.example.megatrustchallenge.dataLayer.room.RoomService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel(application: Application) : AndroidViewModel(application) {
    var jobsData : MutableLiveData<Jobs> = MutableLiveData()
    var progressBar : MutableLiveData<Boolean> = MutableLiveData()
    var itemClick : MutableLiveData<JobsItem> = MutableLiveData()
    var favouriteClick : MutableLiveData<JobsItem> = MutableLiveData()
    var offline : MutableLiveData<Boolean> = MutableLiveData()
    private val onlineRepository = OnlineRepository(ApiClient.apiService)

    private val roomService = RoomReposatory(application)

    fun saveTORoom(item: JobsItem){
        roomService.saveJobItem(item)
    }
    fun getAllData()= roomService.getAllItems()

    fun getJobsData(context: Context){
        if (isOnline(context)) {
            CoroutineScope(Dispatchers.IO).launch {
                onlineRepository.getJobs().enqueue(object : Callback<Jobs?> {
                    override fun onResponse(call: Call<Jobs?>, response: Response<Jobs?>) {
                        if (response.isSuccessful) {
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
        }else{
            offline.value= true
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}