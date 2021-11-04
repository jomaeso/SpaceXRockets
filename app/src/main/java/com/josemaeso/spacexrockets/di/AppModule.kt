package com.josemaeso.spacexrockets.di

import android.content.Context
import com.josemaeso.spacexrockets.data.RoomDatabase
import com.josemaeso.spacexrockets.data.rocket.RocketRepository
import com.josemaeso.spacexrockets.data.rocket.loader.HttpRocketLoader
import com.josemaeso.spacexrockets.data.rocket.loader.api.SpaceXApiService
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractor
import com.josemaeso.spacexrockets.domain.rocket.RocketInteractorImpl
import com.josemaeso.spacexrockets.data.RocketMapper
import com.josemaeso.spacexrockets.ui.loader.PicassoImageLoader
import com.josemaeso.spacexrockets.ui.loader.UIImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun rocketInteractorProvider(@ApplicationContext appContext: Context): RocketInteractor {
        val rocketDao = RoomDatabase.getDatabase(appContext).rocketDao()
        return RocketInteractorImpl(
            HttpRocketLoader(retrofitLoader(), RocketMapper()),
            RocketRepository(rocketDao, RocketMapper()),
            RocketRepository(rocketDao, RocketMapper()),
            GlobalScope,
            Dispatchers.IO
        )
    }

    @Provides
    @Singleton
    fun imageLoaderProvider(): UIImageLoader {
        return PicassoImageLoader()
    }

    private fun retrofitLoader(): SpaceXApiService {
        val retrofit = Retrofit.Builder().baseUrl("https://api.spacexdata.com").addConverterFactory(
            MoshiConverterFactory.create())
            .build()
        return retrofit.create(SpaceXApiService::class.java)
    }
}