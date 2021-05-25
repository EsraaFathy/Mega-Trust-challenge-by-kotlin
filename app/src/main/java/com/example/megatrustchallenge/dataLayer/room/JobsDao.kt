package com.example.megatrustchallenge.dataLayer.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.megatrustchallenge.dataLayer.model.JobsItem

@Dao
interface JobsDao {

    @Query("SELECT * FROM JobsItem ")
    fun getAllJobsItem(): LiveData<List<JobsItem>>

    @Query("SELECT * FROM JobsItem WHERE id LIKE:id ")
    fun getFavourite(id:String): LiveData<JobsItem>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJobsItem(jobsItem: JobsItem)

    @Query("DELETE FROM JobsItem WHERE id LIKE:id")
    suspend fun deleteJobsItem(id:String)

}