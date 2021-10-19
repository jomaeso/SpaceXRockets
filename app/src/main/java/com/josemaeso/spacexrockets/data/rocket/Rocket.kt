package com.josemaeso.spacexrockets.data.rocket

import androidx.room.Entity
import androidx.room.PrimaryKey

typealias ImageUrl = String

@Entity
data class Rocket(
    @PrimaryKey val rocketId: String,
    val name: String,
    val active: Boolean,
    val country: String,
    val company: String,
    val description: String,
    val images: List<ImageUrl>
)
