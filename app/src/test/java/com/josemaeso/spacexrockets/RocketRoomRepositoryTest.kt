package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.rocket.RocketRoom
import com.josemaeso.spacexrockets.data.rocket.RocketDao
import com.josemaeso.spacexrockets.data.rocket.RocketRepository
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RocketRoomRepositoryTest {

    @Mock
    private lateinit var rocketDaoMock: RocketDao

    @Captor
    private lateinit var rocketRoomCaptor: ArgumentCaptor<RocketRoom>

    @Captor
    private lateinit var rocketRoomListCaptor: ArgumentCaptor<List<RocketRoom>>

    @Test
    fun test_getRockets_correct() = runBlocking {
        val sut = makeSUT(rocketDaoMock)
        val rocketId = "id1"
        val flowRockets: Flow<List<RocketRoom>> =
            flow { emit(listOf(RocketDataTestUtil.createRocketRocketRoom(rocketId = rocketId))) }
        `when`(rocketDaoMock.getAll()).thenReturn(flowRockets)

        val rockets = sut.getRockets()

        verify(rocketDaoMock, times(1)).getAll()
        Assert.assertEquals(
            rocketId,
            rockets.first()[0].rocketId
        )
    }

    @Test
    fun test_getRocketById_correct() = runBlocking {
        val sut = makeSUT(rocketDaoMock)

        val rocketId = "id2"
        val flowRocketRoom: Flow<RocketRoom> =
            flow { emit(RocketDataTestUtil.createRocketRocketRoom(rocketId = rocketId)) }
        `when`(rocketDaoMock.getById(rocketId)).thenReturn(flowRocketRoom)

        val rocket = sut.getRocket(rocketId)

        verify(rocketDaoMock, times(1)).getById(rocketId)
        Assert.assertEquals(rocketId, rocket.first().rocketId)
    }

    @Test
    fun test_insertRocket_correct() = runBlocking {
        val sut = makeSUT(rocketDaoMock)
        val rocket = RocketDataTestUtil.createRocket()

        sut.insertRocket(rocket)

        verify(rocketDaoMock, times(1)).insert(MockitoUtils.capture(rocketRoomCaptor))
        Assert.assertEquals(rocket.rocketId, rocketRoomCaptor.value.rocketId)
    }

    @Test
    fun test_insertRockets_correct() = runBlocking {
        val sut = makeSUT(rocketDaoMock)
        val rockets = listOf(
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket()
        )

        sut.insertRockets(rockets)

        verify(rocketDaoMock, times(1)).insert(MockitoUtils.capture(rocketRoomListCaptor))
        Assert.assertEquals(
            rockets.map { it.rocketId },
            rocketRoomListCaptor.value.map { it.rocketId })
    }

    private fun makeSUT(rocketDao: RocketDao): RocketRepository {
        return RocketRepository(rocketDao, RocketMapper())
    }
}