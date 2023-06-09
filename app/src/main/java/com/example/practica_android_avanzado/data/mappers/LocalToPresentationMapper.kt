package com.example.practica_android_avanzado.data.mappers

import com.example.practica_android_avanzado.data.local.model.LocalHero
import com.example.practica_android_avanzado.ui.model.Hero
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {

    fun mapLocalSuperheros(localSuperheros: List<LocalHero>): List<Hero> {
        return localSuperheros.map { mapLocalSuperheros(it) }
    }

    fun mapLocalSuperheros(getHerosResponse: LocalHero): Hero {
        return Hero(getHerosResponse.id, getHerosResponse.name, getHerosResponse.description, getHerosResponse.photo, getHerosResponse.favorite)
    }
}
