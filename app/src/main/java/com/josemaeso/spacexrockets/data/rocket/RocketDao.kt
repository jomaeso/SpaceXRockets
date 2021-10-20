package com.josemaeso.spacexrockets.data.rocket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {
    @Query("SELECT * FROM Rocket ORDER BY name ASC")
    fun getAll(): Flow<List<Rocket>>

    @Query("SELECT * FROM Rocket WHERE rocketId = :id")
    fun getById(id: String): Flow<Rocket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rocket: Rocket)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rockets: List<Rocket>)
}