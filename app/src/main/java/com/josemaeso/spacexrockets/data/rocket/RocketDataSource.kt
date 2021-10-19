package com.josemaeso.spacexrockets.data.rocket

import kotlinx.coroutines.flow.Flow

interface RocketDataSource {
    fun getRockets(): Flow<List<Rocket>>
    fun getRocket(id: String): Flow<Rocket>
    fun insertRockets(rockets: List<Rocket>)
    fun insertRocket(rocket: Rocket)
}