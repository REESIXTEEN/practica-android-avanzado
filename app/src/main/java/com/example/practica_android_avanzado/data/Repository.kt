package com.example.practica_android_avanzado.data

import android.content.SharedPreferences
import com.example.practica_android_avanzado.data.local.LocalDataSource
import com.example.practica_android_avanzado.data.mappers.LocalToPresentationMapper
import com.example.practica_android_avanzado.data.mappers.RemoteToLocalMapper
import com.example.practica_android_avanzado.data.remote.RemoteDataSource
import com.example.practica_android_avanzado.ui.model.Hero
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val shared: SharedPreferences
){

    suspend fun login(email: String, password: String): String {
        return remoteDataSource.login(email, password)
    }

    suspend fun getHeros(): List<Hero> {
        if (localDataSource.getHeros().isEmpty()) {
            val remoteSuperheros = remoteDataSource.getHeros()
            localDataSource.insertHeros(remoteToLocalMapper.mapGetHeroResponse(remoteSuperheros))
        }

        return localToPresentationMapper.mapLocalSuperheros(localDataSource.getHeros())
    }


}