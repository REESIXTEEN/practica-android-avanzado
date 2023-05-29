package com.example.practica_android_avanzado.data.fakes

import com.example.practica_android_avanzado.data.local.LocalDataSource
import com.example.practica_android_avanzado.data.local.SuperheroDAO
import com.example.practica_android_avanzado.data.local.model.LocalHero
import com.example.practica_android_avanzado.utils.generateLocalHeros
import javax.inject.Inject

class FakeLocalDataSource @Inject constructor(dao: SuperheroDAO) : LocalDataSource(dao) {

    private var firstTime = true

    private var heros = mutableListOf<LocalHero>()

    override suspend fun getHeros(): List<LocalHero> {
        return if (firstTime){
            firstTime = false
            emptyList()
        }  else {
            heros
        }
    }

    override suspend fun insertHeros(localSuperheros: List<LocalHero>) {
        heros.addAll(localSuperheros)
    }
}