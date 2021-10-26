package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.rocket.loader.HttpRocketLoader
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class HttpRocketLoaderTest {

    @Mock
    private lateinit var apiServiceMock: SpaceXApiService

    @Test
    fun test_listRockets_success() = runBlocking {
        val remoteRocketsAPI = listOf(
            RemoteRocketTestUtils.createRemoteRocketAPI(),
            RemoteRocketTestUtils.createRemoteRocketAPI(),
            RemoteRocketTestUtils.createRemoteRocketAPI()
        )

        whenever(apiServiceMock.listRockets()).thenReturn(Response.success(remoteRocketsAPI))
        val sut = makeSUT(apiServiceMock)

        val remoteRockets = sut.loadRockets()

        assertEquals(remoteRocketsAPI.map { it.id }, remoteRockets.map { it.rocketId })
    }

    private fun makeSUT(apiService: SpaceXApiService): HttpRocketLoader {
        return HttpRocketLoader(apiService, RocketMapper())
    }
}