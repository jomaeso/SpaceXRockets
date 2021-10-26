package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.rocket.loader.HttpRocketLoader
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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

    @Test
    fun test_listRockets_successEmpty() = runBlocking {
        whenever(apiServiceMock.listRockets()).thenReturn(Response.success(listOf()))
        val sut = makeSUT(apiServiceMock)

        val remoteRockets = sut.loadRockets()

        assertTrue(remoteRockets.isEmpty())
    }

    @Test
    fun test_listRockets_error() = runBlocking {

        whenever(apiServiceMock.listRockets()).thenReturn(
            Response.error(
                400,
                ResponseBody.create(null, "")
            )
        )
        val sut = makeSUT(apiServiceMock)

        val remoteRockets = sut.loadRockets()

        assertTrue(remoteRockets.isEmpty())
    }

    @Test
    fun test_loadRocket_success() = runBlocking {
        val rocketId = "remoteId"
        val remoteRocketAPI =
            RemoteRocketTestUtils.createRemoteRocketAPI(rocketId = rocketId)


        whenever(apiServiceMock.getRocket(rocketId)).thenReturn(Response.success(remoteRocketAPI))
        val sut = makeSUT(apiServiceMock)

        val remoteRocket = sut.loadRocket(rocketId)

        assertEquals(rocketId, remoteRocket?.rocketId)
    }

    @Test
    fun test_loadRocket_successEmpty() = runBlocking {
        val rocketId = "remoteId"
        whenever(apiServiceMock.getRocket(rocketId)).thenReturn(Response.success(null))
        val sut = makeSUT(apiServiceMock)

        val remoteRocket = sut.loadRocket(rocketId)

        assertEquals(null, remoteRocket)
    }

    @Test
    fun test_loadRocket_error() = runBlocking {
        val rocketId = "remoteId"
        whenever(apiServiceMock.getRocket(rocketId)).thenReturn(
            Response.error(
                400,
                ResponseBody.create(null, "")
            )
        )
        val sut = makeSUT(apiServiceMock)

        val remoteRocket = sut.loadRocket(rocketId)

        assertEquals(null, remoteRocket)
    }

    private fun makeSUT(apiService: SpaceXApiService): HttpRocketLoader {
        return HttpRocketLoader(apiService, RocketMapper())
    }
}