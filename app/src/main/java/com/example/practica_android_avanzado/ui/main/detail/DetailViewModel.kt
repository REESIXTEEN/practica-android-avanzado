package com.example.practica_android_avanzado.ui.main.detail

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
class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _detailStatus = MutableStateFlow<Detailtatus>(Detailtatus.Loading)
    val detailStatus: StateFlow<Detailtatus> = _detailStatus


    fun getHero(id: String) {
        _detailStatus.value = Detailtatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val hero = repository.getHero(id)
                Log.i("TAG", "Hero obtained from room")
                _detailStatus.update { Detailtatus.Success(hero) }
            }catch (e: Exception) {
                _detailStatus.value = Detailtatus.Error("Something went wrong. $e")
            }
        }
    }

    sealed class Detailtatus {
        object Loading : Detailtatus()
        data class Error(val error: String) : Detailtatus()
        data class Success(val hero: Hero) : Detailtatus()
    }
}

