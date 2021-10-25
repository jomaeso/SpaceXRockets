package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.rocket.loader.HttpRocketLoader
import com.josemaeso.spacexrockets.data.rocket.loader.api.RemoteRocketAPI
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class HttpRocketRoomLoaderTest {

    @Mock
    private lateinit var apiService: SpaceXApiService
    @Mock
    private lateinit var callRockets: Call<List<RemoteRocketAPI>>
    @Mock
    private lateinit var callRocket: Call<RemoteRocketAPI?>

    @Test
    fun test_listRockets_success() {
        val remoteRocketsAPI = listOf(
            RemoteRocketTestUtils.createRemoteRocketAPI(),
            RemoteRocketTestUtils.createRemoteRocketAPI(),
            RemoteRocketTestUtils.createRemoteRocketAPI()
        )
        `when`(callRockets.execute()).thenReturn(Response.success(remoteRocketsAPI))
        `when`(apiService.listRockets()).thenReturn(callRockets)
        val sut = makeSUT(apiService)

        val remoteRockets = sut.loadRockets()

        assertEquals(remoteRocketsAPI.map { it.id }, remoteRockets.map { it.rocketId })
    }

    private fun makeSUT(apiService: SpaceXApiService): HttpRocketLoader {
        return HttpRocketLoader(apiService, RocketMapper())
    }
}