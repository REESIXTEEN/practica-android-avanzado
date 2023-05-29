package com.example.practica_android_avanzado.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica_android_avanzado.data.Repository
import com.example.practica_android_avanzado.ui.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository,
    private val shared: SharedPreferences): ViewModel() {

    var heroes: List<Hero> = listOf()

    private val _mainStatus = MutableStateFlow<MainStatus>(MainStatus.Loading)
    val mainStatus: StateFlow<MainStatus> = _mainStatus

    fun deleteToken() {
        shared.edit().remove("token").apply()
    }

    fun getHeros() {
        _mainStatus.value = MainStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                heroes = repository.getHeros()
                Log.i("TAG", "Heroes obtained from api")
                _mainStatus.update { MainStatus.Success }
            }catch (e: Exception) {
                _mainStatus.value = MainStatus.Error("Something went wrong. $e")
            }
        }

    }

    sealed class MainStatus {
        object Loading : MainStatus()
        data class Error(val error: String) : MainStatus()
        object Success : MainStatus()
    }
}

