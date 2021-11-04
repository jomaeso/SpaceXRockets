package com.josemaeso.spacexrockets.data.rocket

import com.josemaeso.spacexrockets.domain.rocket.RocketReadDataSource
import com.josemaeso.spacexrockets.domain.rocket.RocketWriteDataSource
import com.josemaeso.spacexrockets.data.RocketMapper
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RocketRepository(private val rocketDao: RocketDao, private val mapper: RocketMapper): RocketWriteDataSource, RocketReadDataSource {
    override fun getRockets(): Flow<List<Rocket>> {
        return rocketDao.getAll().map { mapper.reverseMapList(it) }
    }

    override fun getRocket(id: String): Flow<Rocket> {
        return rocketDao.getById(id).map { mapper.reverseMap(it) }
    }

    override suspend fun insertRockets(rockets: List<Rocket>) {
        rocketDao.insert(mapper.mapList(rockets))
    }

    override suspend fun insertRocket(rocket: Rocket) {
        rocketDao.insert(mapper.map(rocket))
    }
}