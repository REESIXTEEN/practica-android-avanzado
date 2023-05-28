package com.example.practica_android_avanzado.data.remote.api

import com.example.practica_android_avanzado.data.remote.request.GetHerosRequestBody
import com.example.practica_android_avanzado.data.remote.request.HeroFavRequestBody
import com.example.practica_android_avanzado.data.remote.response.GetHerosResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DragonBallApi {

    @POST("/api/auth/login")
    suspend fun login(@Header("Authorization") credentials: String): String

    @POST("api/heros/all")
    suspend fun getHeros(@Header("Authorization") authorization: String, @Body getHerosRequestBody: GetHerosRequestBody): List<GetHerosResponse>

    @POST("api/data/herolike")
    suspend fun updateHeroFav(@Header("Authorization") authorization: String, @Body heroFavRequestBody: HeroFavRequestBody)


}