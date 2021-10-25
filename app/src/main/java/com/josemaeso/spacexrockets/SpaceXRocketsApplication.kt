package com.josemaeso.spacexrockets

import android.app.Application
import com.josemaeso.spacexrockets.data.RoomDatabase
import com.josemaeso.spacexrockets.data.rocket.RocketRepository
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractorImpl
import com.josemaeso.spacexrockets.data.rocket.loader.HttpRocketLoader
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import com.josemaeso.spacexrockets.domain.rocket.model.RocketMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpaceXRocketsApplication : Application() {
    lateinit var rocketInteractor: RocketInteractor
    override fun onCreate() {
        super.onCreate()

        val rocketDao = RoomDatabase.getDatabase(applicationContext).rocketDao()
        rocketInteractor = RocketInteractorImpl(
            HttpRocketLoader(retrofitLoader(), RocketMapper()),
            RocketRepository(rocketDao, RocketMapper()),
            RocketRepository(rocketDao, RocketMapper()),
            GlobalScope,
            Dispatchers.IO
        )
    }

    private fun retrofitLoader(): SpaceXApiService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.spacexdata.com").addConverterFactory(
            GsonConverterFactory.create())
            .build()
        return retrofit.create(SpaceXApiService::class.java)
    }
}