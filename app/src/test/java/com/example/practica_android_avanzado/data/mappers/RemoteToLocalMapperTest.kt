package com.example.practica_android_avanzado.data.mappers

import com.example.practica_android_avanzado.ui.model.HeroLocation
import com.example.practica_android_avanzado.utils.generateGetHerosResponse
import com.example.practica_android_avanzado.utils.generateHeroLocations
import com.example.practica_android_avanzado.utils.generateLocalHeros
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RemoteToLocalMapperTest {

    private val remoteToLocalMapper = RemoteToLocalMapper()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_mapGetHeroResponse() {
        val data = generateGetHerosResponse(10)
        val result = remoteToLocalMapper.mapGetHeroResponse(data)
        val expected = generateLocalHeros(10)
        assertEquals(expected,result)

    }

    @Test
    fun test_mapGetHeroLocationResponse() {
        val data = generateHeroLocations(10)
        val result = remoteToLocalMapper.mapGetHeroLocationResponse(data)
        val expected = HeroLocation(0.0,0.0)
        assertEquals(expected,result)
    }


}