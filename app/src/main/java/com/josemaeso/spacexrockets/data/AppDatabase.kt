package com.josemaeso.spacexrockets.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josemaeso.spacexrockets.data.converters.Converters
import com.josemaeso.spacexrockets.data.rocket.RocketRoom
import com.josemaeso.spacexrockets.data.rocket.RocketDao

@Database(entities = [RocketRoom::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rocketDao(): RocketDao
}
