package com.josemaeso.spacexrockets

import android.app.Application
import com.josemaeso.spacexrockets.data.RoomDatabase
import com.josemaeso.spacexrockets.data.rocket.RocketRepository
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractorImpl
import com.josemaeso.spacexrockets.data.rocket.loader.HttpRocketLoader
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpaceXRocketsApplication : Application() {
    lateinit var rocketInteractor: RocketInteractor
    override fun onCreate() {
        super.onCreate()

        rocketInteractor = RocketInteractorImpl(
            HttpRocketLoader(retrofitLoader()),
            RocketRepository(RoomDatabase.getDatabase(applicationContext).rocketDao())
        )
    }

    private fun retrofitLoader(): SpaceXApiService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.spacexdata.com").addConverterFactory(
            GsonConverterFactory.create())
            .build()
        return retrofit.create(SpaceXApiService::class.java)
    }
}