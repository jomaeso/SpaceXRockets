package com.josemaeso.spacexrockets
import com.josemaeso.spacexrockets.domain.rocket.model.ImageUrl
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import com.josemaeso.spacexrockets.data.rocket.RocketRoom
import java.util.*

object RocketDataTestUtil {
    fun createRocket(
        rocketId: String = UUID.randomUUID().toString(),
        name: String = "a rocket",
        active: Boolean = true,
        country: String = "a country",
        company: String = "a company",
        description: String = "a long description goes here",
        images: List<ImageUrl> = listOf("https://image.url")
    ): Rocket {
        return Rocket(
            rocketId,
            name,
            active,
            country,
            company,
            description,
            images
        )
    }

    fun createRocketRocketRoom(
        rocketId: String = UUID.randomUUID().toString(),
        name: String = "a rocket",
        active: Boolean = true,
        country: String = "a country",
        company: String = "a company",
        description: String = "a long description goes here",
        images: List<ImageUrl> = listOf("https://image.url")
    ): RocketRoom {
        return RocketRoom(
            rocketId,
            name,
            active,
            country,
            company,
            description,
            images
        )
    }
}