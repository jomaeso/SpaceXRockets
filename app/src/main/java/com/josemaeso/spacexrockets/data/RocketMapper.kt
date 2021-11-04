package com.josemaeso.spacexrockets.data

import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocketAPI
import com.josemaeso.spacexrockets.data.rocket.RocketRoom
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket

class RocketMapper {
    fun map(rocket: Rocket): RocketRoom {
        return RocketRoom(
            rocket.rocketId,
            rocket.name,
            rocket.active,
            rocket.country,
            rocket.company,
            rocket.description,
            rocket.images
        )
    }

    fun reverseMap(rocket: RocketRoom): Rocket {
        return Rocket(
            rocket.rocketId,
            rocket.name,
            rocket.active,
            rocket.country,
            rocket.company,
            rocket.description,
            rocket.images
        )
    }

    fun reverseMapApi(remoteRocket: RemoteRocketAPI): Rocket {
        return Rocket(
            remoteRocket.id,
            remoteRocket.name,
            remoteRocket.active,
            remoteRocket.country,
            remoteRocket.company,
            remoteRocket.description,
            remoteRocket.flickr_images
        )
    }

    fun reverseMapApiList(remoteRockets: List<RemoteRocketAPI>): List<Rocket> {
        return remoteRockets.map { reverseMapApi(it) }
    }

    fun mapList(rockets: List<Rocket>): List<RocketRoom> {
        return rockets.map { map(it) }
    }

    fun reverseMapList(rockets: List<RocketRoom>): List<Rocket> {
        return rockets.map { reverseMap(it) }
    }
}