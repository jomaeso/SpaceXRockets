package com.josemaeso.spacexrockets.data.rocket.loader

import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocket

interface RocketLoader {
    fun loadRockets(): List<RemoteRocket>?
    fun loadRocket(rocketId: String): RemoteRocket?
}