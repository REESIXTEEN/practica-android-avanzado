package com.example.practica_android_avanzado.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.practica_android_avanzado.data.local.model.LocalHero


@Dao
interface SuperheroDAO {

    @Query("SELECT * FROM superheros")
    suspend fun getAll(): List<LocalHero>

    @Query("SELECT * FROM superheros WHERE id = :id")
    suspend fun getHero(id: String): List<LocalHero>

    @Update
    suspend fun updateHero(hero: LocalHero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVararg(vararg users: LocalHero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalHero>)

    @Delete
    suspend fun delete(user: LocalHero)
}
