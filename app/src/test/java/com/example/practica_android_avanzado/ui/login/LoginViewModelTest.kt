package com.example.practica_android_avanzado.ui.login

import android.content.SharedPreferences
import com.example.practica_android_avanzado.data.Repository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.junit.After
import org.junit.Before
import java.lang.Exception

class LoginViewModelTests {


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
    fun test_login_success() {
        coEvery { repository.login("email", "password") } returns "token"
        val viewModel = LoginViewModel(repository, shared)
        viewModel.email = "email"
        viewModel.password = "password"
        viewModel.login()
        Thread.sleep(3000) //al hacerse en segundo plano esperamos a que termine
        val expected = LoginViewModel.LoginStatus.TokenReceived
        val result = viewModel.loginStatus.value
        assertEquals(expected, result)

    }

    @Test
    fun test_login_error() {
        coEvery { repository.login("email", "password") } throws  Exception()
        val viewModel = LoginViewModel(repository, shared)
        viewModel.email = "email"
        viewModel.password = "password"
        viewModel.login()
        Thread.sleep(3000) //al hacerse en segundo plano esperamos a que termine
        val expected =
            LoginViewModel.LoginStatus.Error("Error during login. java.lang.Exception")
        val result = viewModel.loginStatus.value
        assertEquals(expected, result)

    }

    @Test
    fun test_credentials() {
        val viewModel = LoginViewModel(repository, shared)
        viewModel.email = ""
        viewModel.password = ""
        viewModel.login()
        val expected = LoginViewModel.LoginStatus.CredentialsError
        val result = viewModel.loginStatus.value
        assertEquals(expected, result)

        viewModel.email = "email"
        viewModel.login()
        val result2 = viewModel.loginStatus.value
        assertEquals(expected, result2)
    }
}