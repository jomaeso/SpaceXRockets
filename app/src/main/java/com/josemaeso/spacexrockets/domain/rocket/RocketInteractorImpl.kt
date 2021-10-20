package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.data.rocket.RocketDataSource
import com.josemaeso.spacexrockets.domain.rocket.loader.RocketLoader
import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RocketInteractorImpl(
    private val rocketLoader: RocketLoader,
    private val rocketDataSource: RocketDataSource
) : RocketInteractor {
    override fun getRockets(): Flow<List<Rocket>> {
        GlobalScope.launch {
            rocketLoader.loadRockets()?.let { remoteRockets ->
                rocketDataSource.insertRockets(remoteRockets.map { remoteRocket ->
                    com.josemaeso.spacexrockets.data.rocket.Rocket(
                        remoteRocket.id,
                        remoteRocket.name,
                        remoteRocket.active,
                        remoteRocket.country,
                        remoteRocket.company,
                        remoteRocket.description,
                        remoteRocket.flickr_images
                    )
                })
            }
        }
        return rocketDataSource.getRockets().map { RocketMapper.reverseMapList(it) }
    }

    override fun getRocket(id: String): Flow<Rocket> {
        GlobalScope.launch {
            rocketLoader.loadRocket(id)?.let { remoteRocket ->
                rocketDataSource.insertRocket(
                    com.josemaeso.spacexrockets.data.rocket.Rocket(
                        remoteRocket.id,
                        remoteRocket.name,
                        remoteRocket.active,
                        remoteRocket.country,
                        remoteRocket.company,
                        remoteRocket.description,
                        remoteRocket.flickr_images
                    )
                )
            }
        }

        return rocketDataSource.getRocket(id).map { RocketMapper.reverseMap(it) }
    }
}