package com.example.practica_android_avanzado.utils

import com.example.practica_android_avanzado.data.local.model.LocalHero
import com.example.practica_android_avanzado.data.remote.response.GetHerosResponse
import com.example.practica_android_avanzado.ui.model.Hero


fun generateHeros(size: Int): List<Hero> {
    return (0 until size).map { Hero("ID $it", "Name $it","Description $it","Photo $it",false) }
}


fun generateGetHerosResponse(size: Int): List<GetHerosResponse> {
    return (0 until size).map { GetHerosResponse("ID $it", "Name $it","Description $it",false,"Photo $it") }
}

fun generateLocalSuperhero(size: Int): List<LocalHero> {
    return (0 until size).map { LocalHero("ID $it", "Name $it","Description $it","Photo $it", false) }
}

