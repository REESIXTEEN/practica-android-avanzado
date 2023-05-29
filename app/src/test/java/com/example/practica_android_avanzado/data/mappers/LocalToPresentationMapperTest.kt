package com.example.practica_android_avanzado.data.mappers

import com.example.practica_android_avanzado.ui.model.HeroLocation
import com.example.practica_android_avanzado.utils.generateGetHerosResponse
import com.example.practica_android_avanzado.utils.generateHeroLocations
import com.example.practica_android_avanzado.utils.generateHeros
import com.example.practica_android_avanzado.utils.generateLocalHeros
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LocalToPresentationMapperTest {

    private val localToPresentation = LocalToPresentationMapper()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_mapGetHeroResponse() {
        val data = generateLocalHeros(10)
        val result = localToPresentation.mapLocalSuperheros(data)
        val expected = generateHeros(10)
        assertEquals(expected,result)
    }

}