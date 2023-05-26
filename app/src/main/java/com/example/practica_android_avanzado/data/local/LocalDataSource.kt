package com.example.practica_android_avanzado.data.local

import com.example.practica_android_avanzado.data.local.model.LocalHero
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: SuperheroDAO) {

    suspend fun getHeros(): List<LocalHero>{
        return dao.getAll()
    }

    suspend fun getHero(id: String): List<LocalHero>{
        return dao.getHero(id)
    }

    suspend fun insertHero(localSuperhero: LocalHero){
        dao.insertAllList(listOf(localSuperhero))
    }

    suspend fun updateHero(hero: LocalHero){
        dao.updateHero(hero)
    }

    suspend fun insertHeros(localSuperheros: List<LocalHero>){
        dao.insertAllList(localSuperheros)
    }

}
