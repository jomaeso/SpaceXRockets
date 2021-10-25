package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.data.rocket.RocketDataSource
import com.josemaeso.spacexrockets.data.rocket.loader.RocketLoader
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RocketInteractorImpl(
    private val rocketLoader: RocketLoader,
    private val rocketDataSource: RocketDataSource
) : RocketInteractor {
    override fun getRockets(): Flow<List<Rocket>> {
        GlobalScope.launch(Dispatchers.IO) {
            rocketLoader.loadRockets()?.let { remoteRockets ->
                rocketDataSource.insertRockets(remoteRockets.map { remoteRocket ->
                    RocketMapper.reverseMapApi(remoteRocket)
                })
            }
        }
        return rocketDataSource.getRockets().map { RocketMapper.reverseMapList(it) }
    }

    override fun getRocket(id: String): Flow<Rocket> {
        GlobalScope.launch(Dispatchers.IO) {
            rocketLoader.loadRocket(id)?.let { remoteRocket ->
                rocketDataSource.insertRocket(
                    RocketMapper.reverseMapApi(remoteRocket)
                )
            }
        }

        return rocketDataSource.getRocket(id).map { RocketMapper.reverseMap(it) }
    }
}