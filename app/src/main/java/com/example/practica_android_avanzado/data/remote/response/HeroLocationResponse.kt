package com.example.practica_android_avanzado.data.remote.response

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity
data class HeroLocationResponse(
    @Json(name = "latitud") val latitud: String,
    @Json(name = "longitud") val longitud: String
)
