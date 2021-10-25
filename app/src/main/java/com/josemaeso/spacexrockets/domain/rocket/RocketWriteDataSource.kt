package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.domain.rocket.model.Rocket

interface RocketWriteDataSource {
    fun insertRockets(rockets: List<Rocket>)
    fun insertRocket(rocket: Rocket)
}