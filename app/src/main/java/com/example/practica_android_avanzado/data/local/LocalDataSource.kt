package com.example.practica_android_avanzado.data.local

import com.example.practica_android_avanzado.data.local.model.LocalHero
import javax.inject.Inject

open class LocalDataSource @Inject constructor(private val dao: SuperheroDAO) {

    open suspend fun getHeros(): List<LocalHero>{
        return dao.getAll()
    }

    suspend fun getHero(id: String): List<LocalHero>{
        return dao.getHero(id)
    }

    suspend fun updateHero(hero: LocalHero){
        dao.updateHero(hero)
    }

    open suspend fun insertHeros(localSuperheros: List<LocalHero>){
        dao.insertAllList(localSuperheros)
    }

}
