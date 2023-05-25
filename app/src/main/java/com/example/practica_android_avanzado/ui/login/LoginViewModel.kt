package com.example.practica_android_avanzado.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica_android_avanzado.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val shared: SharedPreferences): ViewModel() {

    lateinit  var  email: String
    lateinit  var  password: String

    private val _loginStatus = MutableStateFlow<LoginStatus>(LoginStatus.Idle)
    val loginStatus: StateFlow<LoginStatus> = _loginStatus

    fun login(){
        if(checkCredentials()){
            _loginStatus.value = LoginStatus.Loading
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val token = repository.login(email,password)
                    Log.i("TAG", "Token obtained from api")
                    saveToken(token)
                    _loginStatus.value = LoginStatus.TokenReceived
                }catch (e: Exception) {
                    _loginStatus.value = LoginStatus.Error("Error during login. $e")
                }
            }
        }else {
            _loginStatus.value = LoginStatus.CredentialsError
        }
    }

    private fun checkCredentials(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun saveToken(token: String) {
        shared.edit().putString("token", token).apply()
    }

    fun isLogged(): Boolean {
        return !shared.getString("token", "").isNullOrEmpty()
    }


    sealed class LoginStatus{
        object Idle : LoginStatus()
        data class Error(val error: String) : LoginStatus()
        object TokenReceived : LoginStatus()
        object CredentialsError : LoginStatus()
        object Loading : LoginStatus()
    }

}

