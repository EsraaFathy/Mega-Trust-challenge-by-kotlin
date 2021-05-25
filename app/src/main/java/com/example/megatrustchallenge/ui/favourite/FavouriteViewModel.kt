package com.example.megatrustchallenge.ui.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import com.example.megatrustchallenge.dataLayer.room.RoomReposatory

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    var itemClick : MutableLiveData<JobsItem> = MutableLiveData()
    var favouriteClick : MutableLiveData<JobsItem> = MutableLiveData()
    private val roomService = RoomReposatory(application)

    fun getFavourite(id: String) = roomService.getFavourite(id)


    fun deleteFomRoom(id:String){
        roomService.deleteJobItem(id)
    }

    fun getAllFavourires() = roomService.getAllItems()
}