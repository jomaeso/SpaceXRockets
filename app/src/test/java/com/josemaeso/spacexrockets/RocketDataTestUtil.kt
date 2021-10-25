package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.rocket.ImageUrl
import com.josemaeso.spacexrockets.data.rocket.Rocket
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
}