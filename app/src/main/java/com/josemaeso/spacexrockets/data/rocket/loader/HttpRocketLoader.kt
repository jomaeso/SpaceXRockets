package com.josemaeso.spacexrockets.data.rocket.loader

import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocketAPI
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService

class HttpRocketLoader(private val apiService: SpaceXApiService) :
    RocketLoader {
    override fun loadRockets(): List<RemoteRocketAPI>? {
        val call = apiService.listRockets()
        val response = call.execute()
        if (response.isSuccessful) {
            response.body()?.let { remoteRockets ->
                return remoteRockets
            }
        }
        return null
    }

    override fun loadRocket(rocketId: String): RemoteRocketAPI? {
        val call = apiService.getRocket(rocketId)
        val response = call.execute()
        if (response.isSuccessful) {
            response.body()?.let { remoteRocket ->
                return remoteRocket
            }
        }
        return null
    }
}