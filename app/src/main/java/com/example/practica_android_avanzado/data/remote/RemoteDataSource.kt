package com.example.practica_android_avanzado.data.remote

import android.content.SharedPreferences
import com.example.practica_android_avanzado.data.remote.api.DragonBallApi
import com.example.practica_android_avanzado.data.remote.request.GetHerosRequestBody
import com.example.practica_android_avanzado.data.remote.request.HeroFavRequestBody
import com.example.practica_android_avanzado.data.remote.request.HeroLocationRequestBody
import com.example.practica_android_avanzado.data.remote.response.GetHerosResponse
import com.example.practica_android_avanzado.data.remote.response.HeroLocationResponse
import okhttp3.Callback
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api: DragonBallApi, private val shared: SharedPreferences) {

    suspend fun login(email: String, password: String): String {
        val credentials = Credentials.basic(email, password)
        return api.login(credentials)
    }

    suspend fun getHeros(): List<GetHerosResponse> {
        return api.getHeros("Bearer ${getToken()}", GetHerosRequestBody())
    }

    suspend fun updateHeroFav(heroId: String) {
        return api.updateHeroFav("Bearer ${getToken()}", HeroFavRequestBody(heroId))
    }

    suspend fun getHeroLocation(heroId: String): List<HeroLocationResponse> {
        return api.getHeroLocation("Bearer ${getToken()}", HeroLocationRequestBody(heroId))
    }

    private fun getToken(): String {
        return shared.getString("token", "") ?: ""
    }
}