package com.josemaeso.spacexrockets.domain.rocket.model

import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocketAPI
import com.josemaeso.spacexrockets.data.rocket.Rocket as RocketDto

class RocketMapper {
    companion object {
        fun map(rocket: Rocket): RocketDto {
            return RocketDto(
                rocket.rocketId,
                rocket.name,
                rocket.active,
                rocket.country,
                rocket.company,
                rocket.description,
                rocket.images
            )
        }

        fun reverseMap(rocket: RocketDto): Rocket {
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

        fun reverseMapApi(remoteRocket: RemoteRocketAPI): RocketDto {
            return RocketDto(
                remoteRocket.id,
                remoteRocket.name,
                remoteRocket.active,
                remoteRocket.country,
                remoteRocket.company,
                remoteRocket.description,
                remoteRocket.flickr_images
            )
        }

        fun mapList(rockets: List<Rocket>): List<RocketDto> {
            return rockets.map { map(it) }
        }

        fun reverseMapList(rockets: List<RocketDto>): List<Rocket> {
            return rockets.map { reverseMap(it) }
        }
    }
}