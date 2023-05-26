package com.example.practica_android_avanzado.data.mappers

import com.example.practica_android_avanzado.data.local.model.LocalHero
import com.example.practica_android_avanzado.ui.model.Hero
import javax.inject.Inject

class PresentationToLocalMapper @Inject constructor() {

    fun mapPresentationHero(hero: Hero): LocalHero {
        return LocalHero(hero.id, hero.name, hero.description, hero.photo, hero.favorite)
    }

}
