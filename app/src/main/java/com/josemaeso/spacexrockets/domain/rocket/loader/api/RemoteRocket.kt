package com.josemaeso.spacexrockets.domain.rocket.loader.api

typealias ImageUrl = String

data class RemoteRocket(
    val id: String,
    val name: String,
    val active: Boolean,
    val country: String,
    val company: String,
    val description: String,
    val flickr_images: List<ImageUrl>
)