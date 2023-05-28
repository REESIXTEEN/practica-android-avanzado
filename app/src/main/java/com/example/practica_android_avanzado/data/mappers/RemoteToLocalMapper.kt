package com.example.practica_android_avanzado.data.mappers

import com.example.practica_android_avanzado.data.local.model.LocalHero
import com.example.practica_android_avanzado.data.remote.response.GetHerosResponse
import com.example.practica_android_avanzado.data.remote.response.HeroLocationResponse
import com.example.practica_android_avanzado.ui.model.HeroLocation
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){

    fun mapGetHeroResponse(getHerosResponses: List<GetHerosResponse>): List<LocalHero> {
        return getHerosResponses.map { mapGetHeroResponse(it) }
    }

    fun mapGetHeroResponse(getHerosResponse: GetHerosResponse): LocalHero {
        return LocalHero(getHerosResponse.id, getHerosResponse.name,getHerosResponse.description,getHerosResponse.photo, false)
    }

    fun mapGetHeroLocationResponse(getHeroLocationResponse: List<HeroLocationResponse>): HeroLocation {
        return HeroLocation(getHeroLocationResponse.first().latitud.toDouble(), getHeroLocationResponse.first().longitud.toDouble())
    }
}
