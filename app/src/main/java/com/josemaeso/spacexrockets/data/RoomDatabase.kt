package com.josemaeso.spacexrockets.data

import android.content.Context
import androidx.room.Room

class RoomDatabase {
    companion object {
        fun getDatabase(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "spacex-rockets"
            ).build()
        }
    }
}