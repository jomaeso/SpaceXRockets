package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.rocket.loader.api.ImageUrl
import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocketAPI
import java.util.*

object RemoteRocketTestUtils {
    fun createRemoteRocketAPI(
        rocketId: String = UUID.randomUUID().toString(),
        name: String = "a rocket",
        active: Boolean = true,
        country: String = "a country",
        company: String = "a company",
        description: String = "a long description goes here",
        images: List<ImageUrl> = listOf("https://image.url")
    ): RemoteRocketAPI {
        return RemoteRocketAPI(
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