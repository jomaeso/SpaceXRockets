package com.josemaeso.spacexrockets

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.josemaeso.spacexrockets.data.AppDatabase
import com.josemaeso.spacexrockets.data.rocket.RocketRoom
import com.josemaeso.spacexrockets.data.rocket.RocketDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class RocketRoomDaoTest {
    private lateinit var sut: RocketDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        sut = db.rocketDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun test_insertAndGetById_notEmpty() = runBlocking {
        val rocketRoom: RocketRoom = RocketDataTestUtil.createRocketRocketRoom()

        sut.insert(rocketRoom)
        val byId = sut.getById(rocketRoom.rocketId).first()

        assertEquals(rocketRoom, byId)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertAndGetById_replacesInserted() = runBlocking {
        val rocketRoom: RocketRoom = RocketDataTestUtil.createRocketRocketRoom()

        sut.insert(rocketRoom)
        sut.insert(rocketRoom)
        val byId = sut.getById(rocketRoom.rocketId).first()

        assertEquals(rocketRoom, byId)
    }

    @Test
    @Throws(IOException::class)
    fun test_getById_empty() = runBlocking {

        val byId = sut.getById("").first()

        assertEquals(null, byId)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertListAndGetAll() = runBlocking {
        val rockets = listOf(
            RocketDataTestUtil.createRocketRocketRoom(),
            RocketDataTestUtil.createRocketRocketRoom(),
            RocketDataTestUtil.createRocketRocketRoom()
        )

        sut.insert(rockets)
        val resultRockets = sut.getAll().first()

        assertEquals(rockets, resultRockets)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertListAndGetAll_replacesAll() = runBlocking {
        val rockets = listOf(
            RocketDataTestUtil.createRocketRocketRoom(),
            RocketDataTestUtil.createRocketRocketRoom(),
            RocketDataTestUtil.createRocketRocketRoom()
        )

        sut.insert(rockets)
        sut.insert(rockets)
        val resultRockets = sut.getAll().first()

        assertEquals(rockets, resultRockets)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertListAndGetAll_orderedByName() = runBlocking {
        val rockets = listOf(
            RocketDataTestUtil.createRocketRocketRoom(name = "B Rocket"),
            RocketDataTestUtil.createRocketRocketRoom(name = "C Rocket"),
            RocketDataTestUtil.createRocketRocketRoom(name = "A Rocket")
        )

        sut.insert(rockets)
        sut.insert(rockets)
        val resultRockets = sut.getAll().first()

        assertEquals(rockets.sortedBy { it.name }, resultRockets)
    }
}