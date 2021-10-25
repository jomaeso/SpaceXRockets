package com.josemaeso.spacexrockets.data.rocket.loader

import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocket
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService

class HttpRocketLoader(private val apiService: SpaceXApiService) :
    RocketLoader {
    override fun loadRockets(): List<RemoteRocket>? {
        val call = apiService.listRockets()
        val response = call.execute()
        if (response.isSuccessful) {
            response.body()?.let { remoteRockets ->
                return remoteRockets
            }
        }
        return null
    }

    override fun loadRocket(rocketId: String): RemoteRocket? {
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