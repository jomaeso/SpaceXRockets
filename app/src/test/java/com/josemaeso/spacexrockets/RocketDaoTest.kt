package com.josemaeso.spacexrockets

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.josemaeso.spacexrockets.data.AppDatabase
import com.josemaeso.spacexrockets.data.rocket.Rocket
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
class RocketDaoTest {
    private lateinit var rocketDao: RocketDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        rocketDao = db.rocketDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun test_insertAndGetById_notEmpty() = runBlocking {
        val rocket: Rocket = RocketDataTestUtil.createRocket()
        val sut = makeSUT()

        sut.insert(rocket)
        val byId = sut.getById(rocket.rocketId).first()

        assertEquals(rocket, byId)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertAndGetById_replacesInserted() = runBlocking {
        val rocket: Rocket = RocketDataTestUtil.createRocket()
        val sut = makeSUT()

        sut.insert(rocket)
        sut.insert(rocket)
        val byId = sut.getById(rocket.rocketId).first()

        assertEquals(rocket, byId)
    }

    @Test
    @Throws(IOException::class)
    fun test_getById_empty() = runBlocking {
        val sut = makeSUT()

        val byId = sut.getById("").first()

        assertEquals(null, byId)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertListAndGetAll() = runBlocking {
        val sut = makeSUT()
        val rockets = listOf(
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket()
        )

        sut.insert(rockets)
        val resultRockets = sut.getAll().first()

        assertEquals(rockets, resultRockets)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertListAndGetAll_replacesAll() = runBlocking {
        val sut = makeSUT()
        val rockets = listOf(
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket(),
            RocketDataTestUtil.createRocket()
        )

        sut.insert(rockets)
        sut.insert(rockets)
        val resultRockets = sut.getAll().first()

        assertEquals(rockets, resultRockets)
    }

    @Test
    @Throws(IOException::class)
    fun test_insertListAndGetAll_orderedByName() = runBlocking {
        val sut = makeSUT()
        val rockets = listOf(
            RocketDataTestUtil.createRocket(name = "B Rocket"),
            RocketDataTestUtil.createRocket(name = "C Rocket"),
            RocketDataTestUtil.createRocket(name = "A Rocket")
        )

        sut.insert(rockets)
        sut.insert(rockets)
        val resultRockets = sut.getAll().first()

        assertEquals(rockets.sortedBy { it.name }, resultRockets)
    }

    private fun makeSUT(): RocketDao {
        return rocketDao
    }
}