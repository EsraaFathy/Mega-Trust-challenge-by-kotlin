package com.example.megatrustchallenge.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.dataLayer.room.RoomReposatory

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    var favouriteClick : MutableLiveData<JobsItem> = MutableLiveData()
    private val roomService = RoomReposatory(application)

    fun deleteFomRoom(id:String){
        roomService.deleteJobItem(id)
    }

    fun saveItem(jobsItem: JobsItem) =roomService.saveJobItem(jobsItem)


    var itemClick : MutableLiveData<JobsItem> = MutableLiveData()
    fun getFavourite(id: String) = roomService.getFavourite(id)

}