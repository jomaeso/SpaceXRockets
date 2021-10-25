package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketReadDataSource {
    fun getRockets(): Flow<List<Rocket>>
    fun getRocket(id: String): Flow<Rocket>
}