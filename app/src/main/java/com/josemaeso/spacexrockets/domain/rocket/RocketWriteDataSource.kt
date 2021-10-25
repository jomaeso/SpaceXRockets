package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.domain.rocket.model.Rocket

interface RocketWriteDataSource {
    suspend fun insertRockets(rockets: List<Rocket>)
    suspend fun insertRocket(rocket: Rocket)
}