package com.josemaeso.spacexrockets.data.rocket.loader

import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import com.josemaeso.spacexrockets.domain.rocket.RocketLoader
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import com.josemaeso.spacexrockets.data.RocketMapper

class HttpRocketLoader(private val apiService: SpaceXApiService, private val mapper: RocketMapper) :
    RocketLoader {
    override suspend fun loadRockets(): List<Rocket> {
        val response = apiService.listRockets()
        if (response.isSuccessful) {
            response.body()?.let { remoteRockets ->
                return mapper.reverseMapApiList(remoteRockets)
            }
        }
        return emptyList()
    }

    override suspend fun loadRocket(rocketId: String): Rocket? {
        val response = apiService.getRocket(rocketId)
        if (response.isSuccessful) {
            response.body()?.let { remoteRocket ->
                return mapper.reverseMapApi(remoteRocket)
            }
        }
        return null
    }
}