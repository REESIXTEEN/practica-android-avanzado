package com.example.practica_android_avanzado.data.mappers

import com.example.practica_android_avanzado.data.local.model.LocalHero
import com.example.practica_android_avanzado.data.remote.response.GetHerosResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){

    fun mapGetHeroResponse(getHerosResponses: List<GetHerosResponse>): List<LocalHero> {
        return getHerosResponses.map { mapGetHeroResponse(it) }
    }

    fun mapGetHeroResponse(getHerosResponse: GetHerosResponse): LocalHero {
        return LocalHero(getHerosResponse.id, getHerosResponse.name, false)
    }
}
