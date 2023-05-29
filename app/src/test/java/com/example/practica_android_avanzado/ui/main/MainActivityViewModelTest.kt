package com.example.practica_android_avanzado.ui.main

import android.content.SharedPreferences
import com.example.practica_android_avanzado.data.Repository
import com.example.practica_android_avanzado.ui.login.LoginViewModel
import com.example.practica_android_avanzado.utils.generateHeros
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception

class MainActivityViewModelTest {

    private lateinit var repository: Repository
    private lateinit var shared: SharedPreferences

    @Before
    fun setUp() {

        repository = mockk()
        shared = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun test_getHeros_success() {
        coEvery { repository.getHeros() } returns generateHeros(10)
        val viewModel = MainActivityViewModel(repository, shared)
        viewModel.getHeros()
        Thread.sleep(3000) //al hacerse en segundo plano esperamos a que termine
        val expected = MainActivityViewModel.MainStatus.Success
        val result = viewModel.mainStatus.value
        assertEquals(expected, result)

    }

    @Test
    fun test_getHeros_error() {
        coEvery { repository.getHeros() } throws Exception("fatal error")
        val viewModel = MainActivityViewModel(repository, shared)
        viewModel.getHeros()
        Thread.sleep(3000) //al hacerse en segundo plano esperamos a que termine
        val expected = MainActivityViewModel.MainStatus.Error("Something went wrong. java.lang.Exception: fatal error")
        val result = viewModel.mainStatus.value
        assertEquals(expected, result)

    }
}