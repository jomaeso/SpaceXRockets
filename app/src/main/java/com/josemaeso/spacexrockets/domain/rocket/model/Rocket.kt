package com.josemaeso.spacexrockets.domain.rocket.model

typealias ImageUrl = String

data class Rocket (
    val rocketId: String,
    val name: String,
    val active: Boolean,
    val country: String,
    val company: String,
    val description: String,
    val images: List<ImageUrl>
)
