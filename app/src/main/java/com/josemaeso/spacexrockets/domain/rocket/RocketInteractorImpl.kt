package com.josemaeso.spacexrockets.domain.rocket

import com.josemaeso.spacexrockets.domain.rocket.model.Rocket
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RocketInteractorImpl(
    private val rocketLoader: RocketLoader,
    private val rocketWriteDataSource: RocketWriteDataSource,
    private val rocketReadDataSource: RocketReadDataSource,
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : RocketInteractor {
    override fun getRockets(): Flow<List<Rocket>> {
        scope.launch(dispatcher) {
            rocketLoader.loadRockets().let { remoteRockets ->
                rocketWriteDataSource.insertRockets(remoteRockets)
            }
        }
        return rocketReadDataSource.getRockets()
    }

    override fun getRocket(id: String): Flow<Rocket> {
        scope.launch(dispatcher) {
            rocketLoader.loadRocket(id)?.let { remoteRocket ->
                rocketWriteDataSource.insertRocket(remoteRocket)
            }
        }

        return rocketReadDataSource.getRocket(id)
    }
}