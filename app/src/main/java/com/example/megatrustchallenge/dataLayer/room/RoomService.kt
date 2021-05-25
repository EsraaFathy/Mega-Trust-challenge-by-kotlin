package com.example.megatrustchallenge.dataLayer.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.megatrustchallenge.dataLayer.model.JobsItem


@Database(entities = [JobsItem::class], version = 1,exportSchema = false)
abstract class RoomService : RoomDatabase() {
    companion object{
        @Volatile
        private var db :RoomService? =null

        fun getInstance(application: Application): RoomService? {
            synchronized(this) {
                if (db == null)
                    db = Room.databaseBuilder(
                            application, RoomService::class.java, "RoomServiceJobs"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return db
        }

    }


    abstract fun jobDao(): JobsDao

}