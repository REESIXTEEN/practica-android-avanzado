package com.keepcoding.androidavanzado.di

import com.example.practica_android_avanzado.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
//    @Binds
//    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository
//
//    @Binds
//    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

//    @Binds
//    abstract fun bindsRemoteDataSource(remoteDataSource: RemoteDataSource): RemoteDataSource
}

