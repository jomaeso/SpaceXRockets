package com.josemaeso.spacexrockets.data.rocket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {
    @Query("SELECT * FROM Rocket")
    fun getAll(): Flow<List<Rocket>>

    @Query("SELECT * FROM Rocket WHERE rocketId = :id")
    fun getById(id: String): Flow<Rocket>

    @Insert
    fun insert(rocket: Rocket)

    @Insert
    fun insert(rockets: List<Rocket>)
}