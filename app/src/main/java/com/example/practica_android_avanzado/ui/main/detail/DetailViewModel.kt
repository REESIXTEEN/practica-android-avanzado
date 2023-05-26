package com.example.practica_android_avanzado.ui.main.detail

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
class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _detailStatus = MutableStateFlow<DetailStatus>(DetailStatus.Loading)
    val detailStatus: StateFlow<DetailStatus> = _detailStatus
    private lateinit var hero: Hero


    fun getHero(id: String) {
        _detailStatus.value = DetailStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                hero = repository.getHero(id)
                Log.i("TAG", "Hero obtained from room")
                _detailStatus.update { DetailStatus.Success(hero) }
            }catch (e: Exception) {
                _detailStatus.value = DetailStatus.Error("Something went wrong. $e")
            }
        }
    }

    fun updateFav() {
        _detailStatus.value = DetailStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                hero.favorite = !hero.favorite
                repository.updateHero(hero)
                Log.i("TAG", "Hero updated")
                _detailStatus.update { DetailStatus.Success(hero) }
            }catch (e: Exception) {
                _detailStatus.value = DetailStatus.Error("Something went wrong. $e")
            }
        }
    }

    sealed class DetailStatus {
        object Loading : DetailStatus()
        data class Error(val error: String) : DetailStatus()
        data class Success(val hero: Hero) : DetailStatus()
    }
}

