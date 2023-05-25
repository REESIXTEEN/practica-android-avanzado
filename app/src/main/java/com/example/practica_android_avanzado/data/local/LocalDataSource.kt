package com.example.practica_android_avanzado.data.local

import com.example.practica_android_avanzado.data.local.model.LocalHero

interface LocalDataSource {
    suspend fun getHeros(): List<LocalHero>

    suspend fun insertHero(localSuperhero: LocalHero)

    suspend fun insertHeros(localSuperheros: List<LocalHero>)
}
