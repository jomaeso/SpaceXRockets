package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.domain.rocket.model.Rocket

interface RocketLoader {
    suspend fun loadRockets(): List<Rocket>
    suspend fun loadRocket(rocketId: String): Rocket?
}