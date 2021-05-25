package com.example.megatrustchallenge.dataLayer.room

import android.app.Application
import com.example.megatrustchallenge.dataLayer.model.JobsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RoomReposatory(application: Application) {
    private val database=RoomService.getInstance(application)
    private val roomDao= database!!.jobDao()

    fun saveJobItem(jobsItem: JobsItem){
        CoroutineScope(Dispatchers.IO).launch {
            roomDao.saveJobsItem(jobsItem)
        }
    }

    fun getAllItems() = roomDao.getAllJobsItem()

    fun deleteJobItem(id:String){
        CoroutineScope(Dispatchers.IO).launch {
            roomDao.deleteJobsItem(id)
        }
    }

    fun getFavourite(id: String) = roomDao.getFavourite(id)
}