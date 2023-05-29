package com.example.practica_android_avanzado.data

import com.example.practica_android_avanzado.data.fakes.FakeLocalDataSource
import com.example.practica_android_avanzado.data.local.SuperheroDAO
import com.example.practica_android_avanzado.data.mappers.LocalToPresentationMapper
import com.example.practica_android_avanzado.data.mappers.PresentationToLocalMapper
import com.example.practica_android_avanzado.data.mappers.RemoteToLocalMapper
import com.example.practica_android_avanzado.data.remote.RemoteDataSource
import com.example.practica_android_avanzado.utils.generateGetHerosResponse
import com.example.practica_android_avanzado.utils.generateLocalHeros
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RepositoryTest {

    // SUT
    private lateinit var repository: Repository

    // Dependencies
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToPresentationMapper: LocalToPresentationMapper
    private lateinit var presentationToLocalMapper: PresentationToLocalMapper
    private lateinit var dao: SuperheroDAO

    @Before
    fun setup() {
        dao = mockk()
        localDataSource = FakeLocalDataSource(dao)
        remoteDataSource = mockk()

        remoteToLocalMapper = RemoteToLocalMapper()
        localToPresentationMapper = LocalToPresentationMapper()
        presentationToLocalMapper = PresentationToLocalMapper()
        repository = Repository(localDataSource, remoteDataSource, localToPresentationMapper, remoteToLocalMapper, presentationToLocalMapper)
    }


    @Test
    fun test_getHerosEmpty() {
        coEvery { remoteDataSource.getHeros() } returns generateGetHerosResponse(10)

        runBlocking {
            val actual = repository.getHeros()
            assert(actual.isNotEmpty())
        }



    }
}