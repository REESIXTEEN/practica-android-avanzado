package com.keepcoding.androidavanzado.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.practica_android_avanzado.data.local.SuperheroDAO
import com.example.practica_android_avanzado.data.local.SuperheroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesSuperheroDatabase(@ApplicationContext context: Context): SuperheroDatabase {
        return Room.databaseBuilder(
            context,
            SuperheroDatabase::class.java, "superhero-db"
        ).build()
    }

    @Provides
    fun providesDao(db: SuperheroDatabase): SuperheroDAO {
        return db.superheroDao()
    }

    @Provides
    fun providesShared(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    }

}
