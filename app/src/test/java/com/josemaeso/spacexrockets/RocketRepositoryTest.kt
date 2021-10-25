package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.rocket.Rocket
import com.josemaeso.spacexrockets.data.rocket.RocketDao
import com.josemaeso.spacexrockets.data.rocket.RocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RocketRepositoryTest {

    @Mock
    private lateinit var rocketDaoMock: RocketDao

    @Captor
    private lateinit var rocketCaptor: ArgumentCaptor<Rocket>

    @Captor
    private lateinit var rocketListCaptor: ArgumentCaptor<List<Rocket>>

    @Test
    fun test_getRockets_correct() {
        val sut = makeSUT(rocketDaoMock)
        val flowRockets: Flow<List<Rocket>> = flow { listOf(RocketDataTestUtil.createRocket()) }
        `when`(rocketDaoMock.getAll()).thenReturn(flowRockets)

        val rockets = sut.getRockets()

        verify(rocketDaoMock, times(1)).getAll()
        Assert.assertEquals(flowRockets, rockets)
    }

    @Test
    fun test_getRocketById_correct() {
        val sut = makeSUT(rocketDaoMock)
        val flowRocket: Flow<Rocket> = flow { RocketDataTestUtil.createRocket() }
        val rocketId = "an Id"
        `when`(rocketDaoMock.getById(rocketId)).thenReturn(flowRocket)

        val rocket = sut.getRocket(rocketId)

        verify(rocketDaoMock, times(1)).getById(rocketId)
        Assert.assertEquals(flowRocket, rocket)
    }

    @Test
    fun test_insertRocket_correct() {
        val sut = makeSUT(rocketDaoMock)
        val rocket = RocketDataTestUtil.createRocket()

        sut.insertRocket(rocket)

        verify(rocketDaoMock, times(1)).insert(MockitoUtils.capture(rocketCaptor))
        Assert.assertEquals(rocket, rocketCaptor.value)
    }

    @Test
    fun test_insertRockets_correct() {
        val sut = makeSUT(rocketDaoMock)
        val rockets = listOf(
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket()
        )

        sut.insertRockets(rockets)

        verify(rocketDaoMock, times(1)).insert(MockitoUtils.capture(rocketListCaptor))
        Assert.assertEquals(rockets, rocketListCaptor.value)
    }

    private fun makeSUT(rocketDao: RocketDao): RocketRepository {
        return RocketRepository(rocketDao)
    }
}