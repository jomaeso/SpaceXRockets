package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.domain.rocket.model.Rocket

interface RocketLoader {
    fun loadRockets(): List<Rocket>
    fun loadRocket(rocketId: String): Rocket?
}