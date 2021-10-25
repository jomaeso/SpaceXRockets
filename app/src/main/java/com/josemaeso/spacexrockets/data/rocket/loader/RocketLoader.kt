package com.josemaeso.spacexrockets.data.rocket.loader

import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocketAPI

interface RocketLoader {
    fun loadRockets(): List<RemoteRocketAPI>?
    fun loadRocket(rocketId: String): RemoteRocketAPI?
}