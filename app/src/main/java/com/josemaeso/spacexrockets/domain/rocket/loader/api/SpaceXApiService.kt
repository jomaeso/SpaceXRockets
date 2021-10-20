package com.josemaeso.spacexrockets.domain.rocket.loader.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApiService {
    @GET("v4/rockets")
    fun listRockets(): Call<List<RemoteRocket>>

    @GET("v4/rockets/{id}")
    fun getRocket(@Path("id") id: String): Call<RemoteRocket>
}