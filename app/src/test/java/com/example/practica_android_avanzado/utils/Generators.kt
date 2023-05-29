package com.example.practica_android_avanzado.utils

import com.example.practica_android_avanzado.data.local.model.LocalHero
import com.example.practica_android_avanzado.data.remote.response.GetHerosResponse
import com.example.practica_android_avanzado.data.remote.response.HeroLocationResponse
import com.example.practica_android_avanzado.ui.model.Hero
import com.example.practica_android_avanzado.ui.model.HeroLocation


fun generateHeros(size: Int): List<Hero> {
    return (0 until size).map { Hero("ID $it", "Name $it","Description $it","Photo $it",false) }
}


fun generateGetHerosResponse(size: Int): List<GetHerosResponse> {
    return (0 until size).map { GetHerosResponse("ID $it", "Name $it","Description $it",false,"Photo $it") }
}

fun generateLocalHeros(size: Int): List<LocalHero> {
    return (0 until size).map { LocalHero("ID $it", "Name $it","Description $it","Photo $it", false) }
}

fun generateHeroLocations(size: Int): List<HeroLocationResponse> {
    return (0 until size).map { HeroLocationResponse("0.0", "0.0") }
}

