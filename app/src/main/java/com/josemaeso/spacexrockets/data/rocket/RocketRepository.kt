package com.josemaeso.spacexrockets.data.rocket

import kotlinx.coroutines.flow.Flow

class RocketRepository(private val rocketDao: RocketDao): RocketDataSource {
    override fun getRockets(): Flow<List<Rocket>> {
        return rocketDao.getAll()
    }

    override fun getRocket(id: String): Flow<Rocket> {
        return rocketDao.getById(id)
    }

    override fun insertRockets(rockets: List<Rocket>) {
        rocketDao.insert(rockets)
    }

    override fun insertRocket(rocket: Rocket) {
        rocketDao.insert(rocket)
    }
}