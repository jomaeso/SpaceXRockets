package com.josemaeso.spacexrockets.data.rocket.loader.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApiService {
    @GET("v4/rockets")
    suspend fun listRockets(): Response<List<RemoteRocketAPI>>

    @GET("v4/rockets/{id}")
    suspend fun getRocket(@Path("id") id: String): Response<RemoteRocketAPI>
}