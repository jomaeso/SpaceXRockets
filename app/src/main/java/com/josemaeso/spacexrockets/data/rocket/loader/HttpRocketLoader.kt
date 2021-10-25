package com.josemaeso.spacexrockets.data.rocket.loader

import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import com.josemaeso.spacexrockets.domain.rocket.RocketLoader
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper

class HttpRocketLoader(private val apiService: SpaceXApiService, private val mapper: RocketMapper) :
    RocketLoader {
    override fun loadRockets(): List<Rocket> {
        val call = apiService.listRockets()
        val response = call.execute()
        if (response.isSuccessful) {
            response.body()?.let { remoteRockets ->
                return mapper.reverseMapApiList(remoteRockets)
            }
        }
        return emptyList()
    }

    override fun loadRocket(rocketId: String): Rocket? {
        val call = apiService.getRocket(rocketId)
        val response = call.execute()
        if (response.isSuccessful) {
            response.body()?.let { remoteRocket ->
                return mapper.reverseMapApi(remoteRocket)
            }
        }
        return null
    }
}