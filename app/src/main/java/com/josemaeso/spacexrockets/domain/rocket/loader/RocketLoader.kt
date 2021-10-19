package com.josemaeso.spacexrockets.domain.rocket.loader

import com.josemaeso.spacexrockets.domain.rocket.loader.api.RemoteRocket

interface RocketLoader {
    fun loadRockets(): List<RemoteRocket>?
    fun loadRocket(rocketId: String): RemoteRocket?
}