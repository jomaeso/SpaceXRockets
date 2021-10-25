package com.josemaeso.spacexrockets.data.rocket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {
    @Query("SELECT * FROM RocketRoom ORDER BY name ASC")
    fun getAll(): Flow<List<RocketRoom>>

    @Query("SELECT * FROM RocketRoom WHERE rocketId = :id")
    fun getById(id: String): Flow<RocketRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rocketRoom: RocketRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rocketRooms: List<RocketRoom>)
}